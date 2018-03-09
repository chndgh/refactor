package com.company.project.service;


import com.company.project.service.queues.QueuePool;
import com.company.project.service.queues.RedisQueuePool;
import com.company.project.server.quartz.CrawlerTasker;
import com.company.project.service.impl.TSystemlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA. User: rock Date: 3/16/15 Time: 2:34 PM To change
 * this template use File | Settings | File Templates.
 */


public class AbstractAppMaster extends Thread {

    @Autowired
    TSystemlogServiceImpl systemLogUtil;

    public QueuePool queuePool;
    public String redisKey;


    public AbstractAppMaster() {
        this.redisKey = "uumai.taobao";
        System.out.println("redisKey:" + redisKey);
    }

    public AbstractAppMaster init() {

        this.queuePool = new RedisQueuePool(redisKey);

        return this;
    }

    protected void cleanprevioustasker() {
        this.getQueuePool().cleantasks();
    }

    public String getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(String redisKey) {
        this.redisKey = redisKey;
    }

    public QueuePool getQueuePool() {
        return queuePool;
    }

    public void setQueuePool(QueuePool queuePool) {
        this.queuePool = queuePool;
    }

    private String host;
    private String currentTaskerOwner;
    private String currentTaskerName;
    private String currentTaskerSerie;
    private int currentTaskercount = 0;

    public void run() {

        try {
            this.queuePool.connect();
//			systemLogUtil.connect();
            dobusiness();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            queuePool.close();
//			systemLogUtil.close();
        }

        System.out.println("stopped master!");

    }

    public void customerCommand(String command, String[] paramsargs) {

    }

    protected void putDistributeTask(CrawlerTasker crawlerTasker) throws Exception {
        if (this.currentTaskerSerie != crawlerTasker.getTaskerSeries()) {
            // new series,clean
            this.cleanpreviouslog();
        }
        this.host = host;
        this.currentTaskerOwner = crawlerTasker.getTaskerOwner();
        this.currentTaskerName = crawlerTasker.getTaskerName();
        this.currentTaskerSerie = crawlerTasker.getTaskerSeries();
        this.currentTaskercount = this.currentTaskercount + 1;

        // put >1000 , start to check pool size
        if (this.currentTaskercount > 5000) {
            // per 100, check pool once
            if (this.currentTaskercount % 1000 == 0)
                waitingfortaskerfull();
        }
        if (host == null) {
            // super.putDistributeTask(crawlerTasker);
            this.getQueuePool().putDistributeTask(crawlerTasker);

        } else {
            // super.putDistributeTask(host,crawlerTasker);
            this.getQueuePool().putDistributeTask(host, crawlerTasker);

        }
    }

    private String getCurrentLey() {
        if (this.host != null) {
            return this.host + "-" + this.getQueuePool().getRediskey() + "_" + this.currentTaskerOwner + "_"
                    + this.currentTaskerName + "_" + this.currentTaskerSerie;
        }
        return this.getQueuePool().getRediskey() + "_" + this.currentTaskerOwner + "_" + this.currentTaskerName + "_"
                + this.currentTaskerSerie;
    }

    private void waitingfortaskerfull() {
        try {
            String key = getCurrentLey();
            while (true) {
                long currentQueueSize = this.getQueuePool().getQueueSize(key);
                if (currentQueueSize > 5000) {
                    System.out.println("running tasker queue>200! waiting....");
                    System.out.println("current tasker queue size:" + currentQueueSize + ", total count:"
                            + this.currentTaskercount);
                    Thread.sleep(1000 * 5);
                } else {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dobusiness() throws Exception {

    }

    public void waittaskfinished() throws Exception {
        this.waittaskfinished(false);
    }

    protected void waittaskfinished(boolean failedThrowException) throws Exception {
        if (this.currentTaskerName == null || this.currentTaskerOwner == null || this.currentTaskerSerie == null
                || this.currentTaskercount == 0) {
            System.out.println("didn't detect current tasker.");
            return;
        }
        // check redis pool
        String key = getCurrentLey();
        while (true) {
            long currentQueueSize = this.getQueuePool().getQueueSize(key);
            System.out.println("Tasker Owner:" + this.currentTaskerOwner + ",Name:" + this.currentTaskerName
                    + ",Series:" + this.currentTaskerSerie + ",totalcount:" + this.currentTaskercount + ", still have "
                    + currentQueueSize + " not start...");

            if (currentQueueSize > 0) {
                Thread.sleep(1000 * 10);
            } else {
                break;
            }
        }

        // check uumai_logs

        long logscount = 0;
        for (int i = 0; i <= 100; i++) {
            long new_logscount = getFinishedCount();
            if (logscount != new_logscount) {
                i = 0;
                logscount = new_logscount;
            }
            System.out.println("Tasker Owner:" + this.currentTaskerOwner + ",Name:" + this.currentTaskerName
                    + ",Series:" + this.currentTaskerSerie + ",totalcount:" + this.currentTaskercount
                    + ", have finished " + logscount);

            if (logscount / this.currentTaskercount > 0.95) {
                System.out.println("all done!");
                break;
            } else {
                System.out.println("waiting for tasker running...");
                Thread.sleep(1000 * 10);
            }
            if (i == 10) { // wait for 5 mins
                System.out.println("wating too long, stop!");
                break;
            }
        }
        System.out.println("summy totalcount:" + this.currentTaskercount);
        System.out.println("summy logs total count:" + logscount);
        long logfailedscount = getFailedCount();
        System.out.println("summy logs failed count:" + logfailedscount);

        if (logscount != this.currentTaskercount) {
            System.out.println("WARNNING: HAVE MISSED TASKERS:" + (this.currentTaskercount - logscount));
        }

        //clean systemlog
        systemLogUtil.cleanLogs(this.currentTaskerOwner, this.currentTaskerName,
                this.currentTaskerSerie);

    }

    public void cleanpreviouslog() {
        // reset tasker indicator
        this.host = null;
        this.currentTaskerOwner = null;
        this.currentTaskerName = null;
        this.currentTaskerSerie = null;
        this.currentTaskercount = 0;
    }

    public long getFinishedCount() {

        long new_logscount = systemLogUtil.getLogs(this.currentTaskerOwner, this.currentTaskerName,
                this.currentTaskerSerie);
        return new_logscount;
    }

    public long getFailedCount() {
        long new_logscount = systemLogUtil.getLogs(this.currentTaskerOwner, this.currentTaskerName,
                this.currentTaskerSerie, "false");
        return new_logscount;
    }

    public long getSuccessdCount() {
        long new_logscount = systemLogUtil.getLogs(this.currentTaskerOwner, this.currentTaskerName,
                this.currentTaskerSerie, "true");
        return new_logscount;
    }

    public float getFailedRate() {
        float rate = getFailedCount() / getFinishedCount();
        return rate;
    }

}
package com.company.project.service.queues;

import com.company.project.client.util.UumaiProperties;
import com.company.project.client.util.io.SerializeUtil;
import com.company.project.service.redis.RedisDao;
import com.company.project.server.quartz.CrawlerTasker;
import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by rock on 8/29/15.
 */
public class RedisQueuePool extends  QueuePool{
    private final  String host= UumaiProperties.getHostName();

    private RedisDao dao;
    private Jedis redis;
    private Set<String> keySets=new HashSet<String>();

    public  RedisQueuePool(String rediskey){
        super();
        this.rediskey=rediskey;
        this.dao=new RedisDao();
    }

    @Override
    public void connect(){
//        dao.init();
        redis=dao.getRedis();
        checkreloadkeys();
     }

    protected void reloadKeys(){
        this.keySets=redis.keys(rediskey+"*");
    }



//    public List<String> blpop() {
//        for(int i=1;i<=maxlevel;i++){
//            List<String> result= redis.blpop(1, this.rediskey+i);
//            if(result!=null&&result.size()!=0)
//                return result;
//        }
//       return null;
//    }
//
//    public List<String> brpop() {
//        for(int i=1;i<=maxlevel;i++){
//            List<String> result= redis.brpop(1, this.rediskey + i);
//            if(result!=null&&result.size()!=0)
//                return result;
//        }
//        return null;
//    }
//    @Override
//    public Long llen(int level){
//        level=checklevel(level);
//        return  redis.llen(rediskey+level);
//    }
    @Override
    public List<CrawlerTasker> getDistributeTask(){
        checkreloadkeys();
        runningTakserlist.clear();

         for(String key:this.keySets){
              String result =redis.rpop(key);
              if(result!=null) {
                    CrawlerTasker crawlerTasker=(CrawlerTasker) SerializeUtil.unserialize(result);
                    runningTakserlist.add(crawlerTasker);
             }

        }

        return runningTakserlist;
    }

 

    @Override
    public  void putDistributeTask(CrawlerTasker tasker) {
         try {
             String key=this.rediskey +"_" +tasker.getTaskerOwner()+ "_" + tasker.getTaskerName() +"_"+ tasker.getTaskerSeries();
//            if(putfirst){
//                redis.rpush(rediskey + level, tasker);
//            }else {
                    redis.lpush(key, SerializeUtil.serialize(tasker));
//                redis.lpush(rediskey+level, tasker);
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * this method will called by all the crawlerworker ,so need synchronized .and need to get redis connection by itselef.
     * @param tasker
     */
        @Override
    public synchronized void removeRunningList(CrawlerTasker tasker){

    }

//    @Override
//    public int getNotFinishedTasker(int level){
//        level=checklevel(level);
//        int returnkey=-1;
//        try {
//            returnkey=redis.llen(rediskey+level).intValue();
////            dao.returnResource(redis);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return returnkey;
//    }
    @Override
    public void close(){
        dao.returnResource(redis);
//        dao.destroy();
    }
    @Override
    public void cleantasks() {
        this.reloadKeys();
        for(String key:keySets){
            this.cleantasks(key);
        }
//        this.cleantasks(rediskey+__runingKey);
    }


    private void cleantasks(String key){
        try {
            redis.del(key);
//            dao.returnResource(redis);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void checkRunningPool() {
//        while (true){
//            String msg= redis.rpop(rediskey+__runingKey);
////            String msg=redis.spop(rediskey+__runingKey);
//            if(msg!=null){
//                System.out.println("found not finish running tasker, send to pool back.");
//                this.putDistributeTask(msg);
//            }else{
//                break;
//            }
//        }
    }

    @Override
    public Long  getQueueSize(String key) {
        return redis.llen(key);
    }

    }

package com.company.project.server.quartz;


import com.company.project.model.TSystemlog;
import com.company.project.server.download.Download;
import com.company.project.server.download.DownloadFactory;
import com.company.project.server.logs.SystemLogUtil;
import com.company.project.server.queues.QueuePool;
import com.company.project.service.impl.TSystemlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kanxg on 14-12-18.
 */
@Service
public class MultiCrawlerWorker extends CrawlerWorker {
	// private String orignTaskerMsg;

	@Autowired
	TSystemlogServiceImpl systemlogService;


	private QueuePool queuePool;

	// public CrawlerWorker(CrawlerTasker tasker,DefaultFixThreadPool pool) {
	// this(tasker, pool,true);
	// }
	private TSystemlog systemlog;
	private SystemLogUtil systemLogUtil=new SystemLogUtil();
	
	public MultiCrawlerWorker(CrawlerTasker tasker) {
		super(tasker);
	}

	// public CrawlerWorker(CrawlerTasker tasker,WorkerPool workerPool,QueuePool
	// queuePool) {
	// this.tasker=tasker;
	// this.workerPool=workerPool;
	// this.queuePool=queuePool;
	//// this.resourcePool=resourcePool;
	//// this.queue=pool.queue;
	// }

	public void run() {
		// while (true){
		// CrawlerTasker tasker=this.pool.pollTask(); //queue.poll();
		if (tasker == null) {
			// try {
			System.out.println("tasker is null!");
			// Thread.sleep(1000);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
		} else {
			System.out.println("Thread " + Thread.currentThread().getName() + " do task " + tasker.getUrl());
			// System.out.println("Thread "+ Thread.currentThread().getName() +" start do
			// tasker:" + new UumaiTime().getNowString());
			try {
				tasker.init();
				if (tasker.getProxy() == null) {
					// String proxyip = UumaiProperties.readconfig("uumai.crawler.CrawlerProxy.ip",
					// null);
					// String proxyport =
					// UumaiProperties.readconfig("uumai.crawler.CrawlerProxy.port", null);
					// if (proxyip != null && proxyport != null) {
					// tasker.setProxy(new CrawlerProxy(proxyip, new Integer(proxyport)));
					// }
				}
				systemlog=systemLogUtil.createLogInstanceFromTasker(tasker);

				dobusiness();
				// System.out.println("Thread "+ Thread.currentThread().getName() +" finish do
				// tasker:" + new UumaiTime().getNowString());
			} catch (Exception e) {
//				e.printStackTrace();
				try {
					systemLogUtil.createLogInstanceFromException(systemlog, tasker, e);
					failHander();
				} catch (Exception e1) {
//					e1.printStackTrace();
				}
			}

			// System.out.println("Thread "+ Thread.currentThread().getName() +" start save
			// log:" + new UumaiTime().getNowString());

			// record tasker result into logs.
			// record into mongodb
			try {
				// }
				systemlogService.createLog(systemlog);
				// remove from back redis pool list
				this.getQueuePool().removeRunningList(tasker);
			} catch (Exception e) {
				e.printStackTrace(); // To change body of catch statement use
				// File | Settings | File Templates.
			}
			// System.out.println("Thread "+ Thread.currentThread().getName() +" finish save
			// log:" + new UumaiTime().getNowString());

			// System.out.println("finish tasker:" + tasker.url);

		}

		// }

	}

	protected void download() throws Exception {

		Download download = getDownloadInstantce();
		this.result = download.download(tasker);
		this.result.setUrl(tasker.getUrl());

	}

	protected Download getDownloadInstantce() throws Exception {
		return DownloadFactory.getnewDownload(tasker.getDownloadType());
	}

	/**
	 * hander when worker get errors,put tasker back to pool.
	 */
	protected void failHander() {
		// if(this.tasker.getMaxRetryTimes_distributed()<=0){
		// System.out.println("distributed retry times less than 0, not retry. ignore
		// tasker.");
		// }else{
		// if(this.tasker.getRetryTimes_distributed()<this.tasker.getMaxRetryTimes_distributed()){
		// this.tasker.setRetryTimes_distributed(this.tasker.getRetryTimes_distributed()+1);
		//// this.workerPool.putDistributeFailedTask(this.tasker);
		// }else{
		// System.out.println("meet max distributed retry times, not retry.ignore
		// tasker");
		// }
		//
		// }\
		// this.getQueuePool().putDistributeTask(this.tasker);

	}

	protected void saveTasker2DB(String tablename, String json) {
		try {
			// if(tasker.isSavingLogs()||!uumaiLog.isResult()){

			// }
			// remove from back redis pool list
		} catch (Exception e) {
			e.printStackTrace(); // To change body of catch statement use
			// File | Settings | File Templates.
		}
	}

	protected void saveFailed2DB(String tablename) {
		try {
			// if(tasker.isSavingLogs()||!uumaiLog.isResult()){

			// }
			// remove from back redis pool list
		} catch (Exception e) {
			e.printStackTrace(); // To change body of catch statement use
			// File | Settings | File Templates.
		}
	}

	public void sendBack2Pool() {
		this.getQueuePool().putDistributeTask(this.tasker);
	}

	public QueuePool getQueuePool() {
		return queuePool;
	}

	public void setQueuePool(QueuePool queuePool) {
		this.queuePool = queuePool;
	}
}

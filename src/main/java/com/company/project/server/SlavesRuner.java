package com.company.project.server;

import com.uumai.server.quartz.AbstractAppSlave;
import com.uumai.server.quartz.QuartzAppSlave;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rock on 4/22/15.
 */
public class SlavesRuner {

    private List<AbstractAppSlave> threadList=new ArrayList<AbstractAppSlave>();

    public SlavesRuner(){
        //register shutdown hook
        new ShutdownHook(threadList);

    }


    public void startserver(){

            String mainclass="" ;//UumaiProperties.readconfig("uumai.crawler.worker.appslave.mainclass/"+child,null);

 //            String redisKey=UumaiProperties.readconfig("uumai.crawler.worker"+i+".appslave.redisKey",null);
             try {
                AbstractAppSlave app=new QuartzAppSlave() ; //(AbstractAppSlave)Class.forName(mainclass).newInstance();
                app.setPoolsize(30);


//                if(redisKey!=null){
//                    app.setRedisKey(redisKey);
//                }
                app.init();
                app.start();
                //app.join();

                threadList.add(app);

            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public static void main(String[] args) throws Exception {
        SlavesRuner runer=new SlavesRuner();

        runer.startserver();
    }

    class  ShutdownHook implements Runnable {
//        private AbstractAppSlave  slave;
        private    List<AbstractAppSlave> threadList;
         public ShutdownHook(List<AbstractAppSlave> threadList) {
            // register a shutdown hook for this class.
            // a shutdown hook is an initialzed but not started thread, which will get up and run
            // when the JVM is about to exit. this is used for short clean up tasks. ;
            this.threadList=threadList  ;
             Runtime.getRuntime().addShutdownHook(new Thread(this));
            System.out.println("slave shutdown hook registered");
        }

        // this method will be executed of course, since it's a Runnable.
        // tasks should not be light and short, accessing database is alright though.
        public void run() {
            // System.out.println("/n>>> About to execute: " + ShutdownHook.class.getName() + ".run() to clean up before JVM exits.");
            this.cleanUp();
            // System.out.println(">>> Finished execution: " + ShutdownHook.class.getName() + ".run()");
        }

        private void cleanUp() {
            for(AbstractAppSlave slave:threadList){
                slave.stoppool();
                try {
                    slave.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //stop threadpool
        }
    }

}

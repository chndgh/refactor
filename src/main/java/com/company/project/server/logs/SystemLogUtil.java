package com.company.project.server.logs;


import com.company.project.client.util.UumaiTime;
import com.company.project.model.TSystemlog;
import com.company.project.server.quartz.CrawlerTasker;

public class SystemLogUtil {

    public TSystemlog createLogInstanceFromTasker(CrawlerTasker tasker) {
        TSystemlog Systemlog = new TSystemlog();
        try {
            Systemlog.setTaskername(tasker.getTaskerName());
            Systemlog.setTaskerseries(tasker.getTaskerSeries());
            Systemlog.setTaskerowner(tasker.getTaskerOwner());
            // Systemlog.setRunHost(UumaiProperties.getHostName());
            Systemlog.setUrl(tasker.getUrl());
            Systemlog.setRuntime(new UumaiTime().getNowString());
            Systemlog.setResult(1);
            if (tasker.getProxy() != null)
                Systemlog.setProxy(tasker.getProxy().toString());
        } catch (Exception e) {
        }
        return Systemlog;
    }

    public void createLogInstanceFromException(TSystemlog Systemlog, CrawlerTasker tasker, Exception e) {
        Systemlog.setResult(-1);
        try {
            StringBuffer sb = new StringBuffer();
            sb.append(e.getMessage());
//            StackTraceElement[] messages = e.getStackTrace();
//            if (messages != null) {
//                for (int i = 0; i < messages.length; i++) {
//                    sb.append("ClassName:" + messages[i].getClassName());
//                    sb.append("getFileName:" + messages[i].getFileName());
//                    sb.append("getLineNumber:" + messages[i].getLineNumber());
//                    sb.append("getMethodName:" + messages[i].getMethodName());
//                    sb.append("toString:" + messages[i].toString());
//                }
//            }
            if (sb.length() > 20000) {
                Systemlog.setErrmessage(sb.substring(0, 20000));
            } else {
                Systemlog.setErrmessage(sb.toString());
            }

            if (tasker.getProxy() != null)
                Systemlog.setProxy(tasker.getProxy().toString());
        } catch (Exception e1) {
        }

    }




}

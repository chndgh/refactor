package com.company.project.server.download;


import com.company.project.server.quartz.CrawlerResult;
import com.company.project.server.quartz.CrawlerTasker;

/**
 * Created by kanxg on 14-12-22.
 */
public interface Download {


    public enum DownloadType{
        java_download, httpclient_download,
        firefox_download, chrome_download,phantomjs_download,jbrowser_download,
        emptymockdown,
        openscript_download,
        file_download,
        shell_download,
        groovy_download,
        android_download
    }
    public CrawlerResult download(CrawlerTasker tasker) throws Exception ;

}

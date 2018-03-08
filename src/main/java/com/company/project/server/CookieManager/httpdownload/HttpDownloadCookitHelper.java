package com.company.project.server.CookieManager.httpdownload;


import com.company.project.server.CookieManager.CookieHelper;
import com.company.project.server.CookieManager.CrawlerCookie;
import com.company.project.server.download.CrawlerProxy;
import com.company.project.server.download.httpdownload.HttpDownload;
import com.company.project.server.quartz.CrawlerResult;
import com.company.project.server.quartz.CrawlerTasker;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rock on 12/7/15.
 */
public class HttpDownloadCookitHelper {

    private CookieHelper cookieHelper=new CookieHelper();
    private HttpDownload httpDownload=new HttpDownload();

    private CrawlerProxy proxy;
    private List<CrawlerCookie> startCookie;
    public HttpDownloadCookitHelper (CrawlerProxy proxy,List<CrawlerCookie> startCookie){
         CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        this.proxy=proxy;
        this.startCookie=startCookie;
    }

    public HttpDownloadCookitHelper (CrawlerProxy proxy){
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        this.proxy=proxy;
    }


    public List<CrawlerCookie> getcookies(String url) throws Exception {
        return this.connect(url, null);
    }
    public List<CrawlerCookie> getcookies(List<String> urllist) throws Exception {
        List<CrawlerCookie> cookie=startCookie;

        if(urllist!=null) {
            for (String url : urllist) {
                     cookie = this.connect(url, cookie);

            }
        }
        return  cookie;
    }

    private List<CrawlerCookie> connect(String urlStr,List<CrawlerCookie> cookies) throws Exception {
        CrawlerTasker tasker=new CrawlerTasker();
        tasker.setUrl(urlStr);
        tasker.setCookies(cookies);
        tasker.setProxy(proxy);
        CrawlerResult crawlerResult=httpDownload.download(tasker);
        return crawlerResult.getCookies();
    }
    public static void main(String[] a) throws  Exception{
//        HttpDownloadCookitHelper httpDownloadCookitHelper=new HttpDownloadCookitHelper(new CrawlerProxy("cn-proxy.jp.oracle.com", 80),null);
        HttpDownloadCookitHelper httpDownloadCookitHelper=new HttpDownloadCookitHelper(null,null);

        List<String> urllist=new ArrayList<String>();
        urllist.add("http://www.linkedin.com");
//        urllist.add("http://xueqiu.com/1130548918");

        List<CrawlerCookie> cookie= httpDownloadCookitHelper.getcookies(urllist);
        for(CrawlerCookie c:cookie) {
            System.out.println("cookie:" + c);
        }

    }
}

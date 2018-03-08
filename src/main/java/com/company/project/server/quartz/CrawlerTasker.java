package com.company.project.server.quartz;



import com.company.project.client.util.UumaiTime;
import com.company.project.server.CookieManager.CrawlerCookie;
import com.company.project.server.download.CrawlerProxy;
import com.company.project.server.download.Download;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kanxg on 14-12-18.
 */
public class CrawlerTasker  implements Comparable<Object> ,Serializable  {

    private static final long serialVersionUID = 1L;

//    int retrytime;

    private String taskerName;
    private String taskerSeries;
    private String taskerOwner;


    //for redis keep origal message;
    private String RedisOrigMessage;
    private String RedisBackupKey;


    private String url;

    private  Download.DownloadType downloadType;

    private  String requestmethod="GET";
    private boolean follingRedirect=true;

    private  String postdata;
    private  String encoding;



    private String crawler_type;

    private CrawlerProxy proxy ;



    private List<CrawlerCookie> cookies;
    private boolean keepCookies=false;

 
    private List<String> requestHeaderList;
 

    public  CrawlerTasker(){

    }

    public void init(){
//        retrytime= new Integer(UumaiProperties.readconfig("uumai.crawler.retrytime","-1"));
//        maxRetryTimes_distributed= new Integer(UumaiProperties.readconfig("uumai.crawler.maxRetryTimes_distributed","-1"));



        //check tasker name and series.
        if(this.getTaskerName()==null){
            this.setTaskerName(this.getClass().getPackage().toString());
        }
        if(this.getTaskerSeries()==null){
            this.setTaskerSeries(new UumaiTime().getNowString());
        }
        if(this.getDownloadType()==null){
            this.setDownloadType(Download.DownloadType.java_download);
        }
    }

    public void addRequestHeader(String header,String value){
        if("".equals(header)) return;
        if("".equals(value)) value=null;

        if(this.getRequestHeaderList()==null){
            this.requestHeaderList=new ArrayList<String>();
        }

        this.requestHeaderList.add(header+":"+value);
    }

    public Download.DownloadType getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(Download.DownloadType downloadType) {
        this.downloadType = downloadType;
    }

 

    public List<CrawlerCookie> getCookies() {
        return cookies;
    }

    public void setCookies(List<CrawlerCookie> cookies) {
        this.cookies = cookies;
    }


    public String getUUID(){
        return url;
    }

    public String getPostdata() {
        return postdata;
    }

    public void setPostdata(String postdata) {
        this.postdata = postdata;
    }

    public String getRequestmethod() {
        return requestmethod;
    }

    public void setRequestmethod(String requestmethod) {
        this.requestmethod = requestmethod;
    }


    public CrawlerProxy getProxy() {
        return proxy;
    }

    public void setProxy(CrawlerProxy proxy) {
        this.proxy = proxy;
    }

    public String getCrawler_type() {
        return crawler_type;
    }

    public void setCrawler_type(String crawler_type) {
        this.crawler_type = crawler_type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getTaskerName() {
        return taskerName;
    }

    public void setTaskerName(String taskerName) {
        this.taskerName = taskerName;
    }

    public String getTaskerSeries() {
        return taskerSeries;
    }

    public void setTaskerSeries(String taskerSeries) {
        this.taskerSeries = taskerSeries;
    }


    public String getTaskerOwner() {
        return taskerOwner;
    }

    public void setTaskerOwner(String taskerOwner) {
        this.taskerOwner = taskerOwner;
    }

 
    public String getRedisOrigMessage() {
        return RedisOrigMessage;
    }

    public void setRedisOrigMessage(String redisOrigMessage) {
        RedisOrigMessage = redisOrigMessage;
    }

    public String getRedisBackupKey() {
        return RedisBackupKey;
    }

    public void setRedisBackupKey(String redisBackupKey) {
        RedisBackupKey = redisBackupKey;
    }

 
    public boolean isKeepCookies() {
        return keepCookies;
    }

    public void setKeepCookies(boolean keepCookies) {
        this.keepCookies = keepCookies;
    }

    public boolean isFollingRedirect() {
        return follingRedirect;
    }

    public void setFollingRedirect(boolean follingRedirect) {
        this.follingRedirect = follingRedirect;
    }

    public List<String> getRequestHeaderList() {
        return requestHeaderList;
    }

 
    @Override
    public int compareTo(Object o) {
        return 0;
    }


    

    



}

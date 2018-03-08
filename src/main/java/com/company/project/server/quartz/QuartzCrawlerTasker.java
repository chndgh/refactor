package com.company.project.server.quartz;


import com.company.project.server.quartz.result.QuartzResultItem;
import com.company.project.server.quartz.result.QuartzXpathItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rock
 * Date: 3/19/15
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class QuartzCrawlerTasker extends CrawlerTasker {
	    
         
//    private String result;
    
//    private int currentCrawlerDeepth=0;
    
//    private String fromUrl;
    
//    private int crawlerDeepth=0;
//    private String continueURLRegularExpress;
    
    private String storeTableName;
    
    private List<QuartzXpathItem> xpathList=new ArrayList<QuartzXpathItem>();
    
    private List<QuartzResultItem> resultItems=new ArrayList<QuartzResultItem>();
    

    public QuartzCrawlerTasker(){

    }
    
 

    public void init(){
        super.init();
//        this.setUseingcache(false);
//        this.setCookies(this.getCookies());
    }
    
    public void addXpath_newrow(){
    	QuartzXpathItem item=new QuartzXpathItem();
    	item.setType(QuartzXpathItem.XpathType._UUMAI_NEWROW_);
    	xpathList.add(item);
    }
    
    public QuartzXpathItem addXpath(String xpathName, String xpath){
    	QuartzXpathItem item=new QuartzXpathItem();
    	item.setType(QuartzXpathItem.XpathType.Xpath);
    	item.setXpathName(xpathName);
    	item.setXpath(xpath);
    	xpathList.add(item);
    	return item;
    }
    public QuartzXpathItem addXpath_all(String xpathName, String xpath){
    	QuartzXpathItem item=new QuartzXpathItem();
    	item.setType(QuartzXpathItem.XpathType.Xpath_ALL);
    	item.setXpathName(xpathName);
    	item.setXpath(xpath);
    	xpathList.add(item);
    	return item;
    }
    
    public QuartzXpathItem addJsonpath(String xpathName, String xpath){
    	QuartzXpathItem item=new QuartzXpathItem();
    	item.setType(QuartzXpathItem.XpathType.JsonPath);
    	item.setXpathName(xpathName);
    	item.setXpath(xpath);
    	xpathList.add(item);
    	return item;
    }
    public QuartzXpathItem addJsonpath_all(String xpathName, String xpath){
    	QuartzXpathItem item=new QuartzXpathItem();
    	item.setType(QuartzXpathItem.XpathType.JsonPath_ALL);
    	item.setXpathName(xpathName);
    	item.setXpath(xpath);
    	xpathList.add(item);
    	return item;
    }
    
    public QuartzXpathItem addRegexExpress(String xpathName, String pattern){
    	QuartzXpathItem item=new QuartzXpathItem();
    	item.setType(QuartzXpathItem.XpathType.REGEX_EXPRESS);
    	item.setXpathName(xpathName);
    	item.setXpath(pattern);
    	xpathList.add(item);
    	return item;
    }
    
    public QuartzXpathItem addRegexExpress_all(String xpathName, String pattern){
    	QuartzXpathItem item=new QuartzXpathItem();
    	item.setType(QuartzXpathItem.XpathType.REGEX_EXPRESS_ALL);
    	item.setXpathName(xpathName);
    	item.setXpath(pattern);
    	xpathList.add(item);
    	return item;
    }
    
    
    public void addResultItem(String name,Object value){
    	QuartzResultItem item=new QuartzResultItem();
    	item.setName(name);
    	item.setValue(value);
    	this.resultItems.add(item);
    }
    public QuartzCrawlerTasker createNextTasker(){
    	QuartzCrawlerTasker newQuartzCrawlerTasker=new QuartzCrawlerTasker();
    	newQuartzCrawlerTasker.setCookies(this.getCookies());
    	newQuartzCrawlerTasker.setTaskerName(this.getTaskerName());
    	newQuartzCrawlerTasker.setTaskerOwner(this.getTaskerOwner());
    	newQuartzCrawlerTasker.setTaskerSeries(this.getTaskerSeries());
    	newQuartzCrawlerTasker.setStoreTableName(storeTableName);
    	newQuartzCrawlerTasker.setXpathList(xpathList);
//    	newQuartzCrawlerTasker.setContinueURLRegularExpress(continueURLRegularExpress);
//    	newQuartzCrawlerTasker.setCurrentCrawlerDeepth(currentCrawlerDeepth+1);
    	newQuartzCrawlerTasker.setDownloadType(this.getDownloadType());
    	return newQuartzCrawlerTasker;
    }


	public String getStoreTableName() {
		return storeTableName;
	}

	public void setStoreTableName(String storeTableName) {
		this.storeTableName = storeTableName;
	}

	public List<QuartzXpathItem> getXpathList() {
		return xpathList;
	}

	public void setXpathList(List<QuartzXpathItem> xpathList) {
		this.xpathList = xpathList;
	}


	public List<QuartzResultItem> getResultItems() {
		return resultItems;
	}

	public void setResultItems(List<QuartzResultItem> resultItems) {
		this.resultItems = resultItems;
	}
	
 

 
    
}
     


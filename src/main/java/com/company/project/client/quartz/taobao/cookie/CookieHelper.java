package com.company.project.client.quartz.taobao.cookie;


import com.company.project.server.CookieManager.CrawlerCookie;

import java.util.ArrayList;
import java.util.List;

public class CookieHelper {
	
	private static List<CrawlerCookie> tmallcookies=new ArrayList<CrawlerCookie>();
	
	private static List<CrawlerCookie> chaoshicookies=new ArrayList<CrawlerCookie>();
	
	private static List<CrawlerCookie> taobaocookies=new ArrayList<CrawlerCookie>();

	static {
		CrawlerCookie cookie = new CrawlerCookie();
		cookie.setName("thw");
		cookie.setValue("cn");
		tmallcookies.add(cookie);
		taobaocookies.add(cookie);
		
		cookie = new CrawlerCookie();
		cookie.setName("cookie2");
		cookie.setValue("1d3eb440de4xxx05148d0daa9ad5f17285b");
		chaoshicookies.add(cookie);
		
		cookie = new CrawlerCookie();
		cookie.setName("t");
		cookie.setValue("d3ddef5e8ace47xaa91b11cfa7cfc3de386");
		chaoshicookies.add(cookie);
		
		cookie = new CrawlerCookie();
		cookie.setName("_tb_token_");
		cookie.setValue("e1881137bbe1b7a");
		chaoshicookies.add(cookie);
	}
	
	public static List<CrawlerCookie> getTmallCookie(){
		return tmallcookies;
	}
	
	public static List<CrawlerCookie> getChaoshiCookie(){
		return chaoshicookies;
	}
	
	public static List<CrawlerCookie> getTaobaoCookie(){
		return taobaocookies;
	}

}

package com.company.project.server.download.selenium;


import com.company.project.server.CookieManager.CookieHelper;
import com.company.project.server.CookieManager.CrawlerCookie;
import com.company.project.server.download.CrawlerProxy;
import com.company.project.server.download.Download;
import com.company.project.server.quartz.CrawlerResult;
import com.company.project.server.quartz.CrawlerTasker;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumDownloader implements Download {
    protected WebDriver webDriver=null;
    protected CrawlerTasker tasker;
	public SeleniumDownloader() {

	}


	@Override
    public CrawlerResult download(CrawlerTasker tasker) throws Exception {
        this.tasker=tasker;
        URL url = new URL(tasker.getUrl());

        try {
            CrawlerProxy proxy=tasker.getProxy();
            if(proxy==null){
                webDriver = WebDriverFactory.getDriver(tasker.getDownloadType(), null);
            }else{
                webDriver = WebDriverFactory.getDriver(tasker.getDownloadType(), proxy);
            }

            WebDriver.Options manage = webDriver.manage();


            webDriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            webDriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);

            if(tasker.getCookies()!=null){

                if(tasker.getDownloadType().equals(DownloadType.jbrowser_download)){
                    webDriver.get(url.getProtocol() + "://" + url.getHost());
                    manage.deleteAllCookies();
                    JavascriptExecutor js = (JavascriptExecutor) webDriver;
                   for(CrawlerCookie crawlerCookie:tasker.getCookies()){
                            js.executeScript("document.cookie = \""+crawlerCookie.getName()+"="+crawlerCookie.getValue()+";path=/;domain="+url.getHost()+"\"");
                    }

                }else if(tasker.getDownloadType().equals(DownloadType.phantomjs_download)){
                    webDriver.get(url.getProtocol() + "://" + url.getHost());
                    manage.deleteAllCookies();
                    JavascriptExecutor js = (JavascriptExecutor) webDriver;
                    for(CrawlerCookie crawlerCookie:tasker.getCookies()){
                        js.executeScript("document.cookie = \""+crawlerCookie.getName()+"="+crawlerCookie.getValue()+";path=/;domain="+url.getHost()+"\"");
                    }

                }else{
                    webDriver.get(url.getProtocol() + "://" + url.getHost());
                    manage.deleteAllCookies();
                    for(CrawlerCookie crawlerCookie:tasker.getCookies()){
                        String domain=crawlerCookie.getDomain();
                        if(domain==null||"".equals(domain)){
                            domain=url.getHost();
                        }
                        Cookie cookie = new Cookie(crawlerCookie.getName(),crawlerCookie.getValue(),domain,
                                crawlerCookie.getPath(),crawlerCookie.getExpiry(),crawlerCookie.isSecure(),crawlerCookie.isHttpOnly() );
                        manage.addCookie(cookie);
                    }
                }


            }

            webDriver.get(tasker.getUrl());
//            System.out.println(webDriver.getPageSource());

            superClassdone();

            return getResult();

        } catch(Exception ex){
           ex.printStackTrace();
            throw  ex;
        }  finally {
            try {
//                webDriver.close();
                webDriver.quit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
    protected void superClassdone() {
    }

        protected CrawlerResult getResult(){
        CrawlerResult crawlerResult=new CrawlerResult();

        CookieHelper cookieHelper=new CookieHelper();
        crawlerResult.setCookies(cookieHelper.parseCookies(webDriver));

        if(tasker.getDownloadType().equals(DownloadType.jbrowser_download)){
            crawlerResult.setRawText(webDriver.getPageSource());
        }else{
//            WebElement webElement = webDriver.findElement(By.xpath("/html"));
//            String content = webElement.getAttribute("innerHTML");
            String content = webDriver.getPageSource();
            crawlerResult.setRawText(content);
        }
        crawlerResult.setReturncode(200);
        return crawlerResult;
    }
}

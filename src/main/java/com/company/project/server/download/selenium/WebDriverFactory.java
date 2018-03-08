package com.company.project.server.download.selenium;


import com.company.project.client.util.UumaiProperties;
import com.company.project.server.download.CrawlerProxy;
import com.company.project.server.download.Download;
import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.ProxyConfig;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by rock on 8/18/15.
 */
public class WebDriverFactory {

    public  static int sleepTime=1000;

    static{
        System.getProperties().setProperty("webdriver.chrome.driver",
                UumaiProperties.getUUmaiHome() + "/driver/chromedriver");

            System.getProperties().setProperty("phantomjs.binary.path",
                UumaiProperties.getUUmaiHome() + "/driver/phantomjs");

        //for selenium 2, firefox
        if(new File( UumaiProperties.getUUmaiHome() + "/driver/webdriver.xpi").exists())
        System.getProperties().setProperty("webdriver.firefox.driver",
                UumaiProperties.getUUmaiHome() + "/driver/webdriver.xpi");
        //for selenium 3  firefox
        System.setProperty("webdriver.gecko.driver", UumaiProperties.getUUmaiHome() + "/driver/geckodriver");

//        System.getProperties().setProperty("HOME",
//                System.getProperty("user.home") );

//        System.getProperties().setProperty("java.io.tmpdir",
//                System.getProperty("user.home") + "/.mozilla/firefox/" );

//        System.setProperty("webdriver.firefox.profile", "default");

    }


    public static synchronized WebDriver getDriver(Download.DownloadType type, CrawlerProxy crawlerProxy){
//        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        DesiredCapabilities capabilities =new DesiredCapabilities();

        if(crawlerProxy!=null){
            // Add the WebDriver proxy capability.
//            Proxy proxy = new Proxy();
//            proxy.setHttpProxy(proxy.get)
//                    .setFtpProxy(proxyIpAndPort)
//                    .setSslProxy(proxyIpAndPort);
//            capabilities.setCapability(CapabilityType.PROXY, proxy);
            // 以下三行是为了避免localhost和selenium driver的也使用代理，务必要加，否则无法与iedriver通讯
            capabilities.setCapability(CapabilityType.ForSeleniumServer.AVOIDING_PROXY, true);
            capabilities.setCapability(CapabilityType.ForSeleniumServer.ONLY_PROXYING_SELENIUM_TRAFFIC, true);
            System.setProperty("http.nonProxyHosts", "localhost");
            System.setProperty("http.nonProxyHosts", "127.0.0.1");

        }

        capabilities.setJavascriptEnabled(true);

        WebDriver e=null;

        if(type== Download.DownloadType.firefox_download){

//            FirefoxProfile profile = new ProfilesIni().getProfile("default");
//            String uumai_profile=getFirefoxPorfilePath("","uumai");
//            if(uumai_profile==null)
//                uumai_profile=getFirefoxPorfilePath("","default");
//            if(uumai_profile==null)
//                uumai_profile=getFirefoxPorfilePath("","uumai");
//            if(uumai_profile==null)
//                uumai_profile=getFirefoxPorfilePath("","default");
//
//            System.out.println("uumai_profile:"+uumai_profile);
//
//            if(uumai_profile!=null){
//                FirefoxProfile  profile = new FirefoxProfile(new File(uumai_profile));
//                capabilities.setCapability("firefox_profile", profile);
//            }
//            ProfilesIni allProfiles = new ProfilesIni();
//            FirefoxProfile profile = allProfiles.getProfile("selenium");
//            e = new FirefoxDriver(profile);


//               capabilities.setCapability("firefox_profile",new ProfilesIni().getProfile("default"));
//            FirefoxProfile  profile = new FirefoxProfile(new File(uumai_profile));
//            capabilities.setCapability("firefox_profile",profile);
            FirefoxBinary firefoxBinary=new FirefoxBinary();
            if("yarn".equalsIgnoreCase(System.getProperty("uumai.runmode"))){
                firefoxBinary.setEnvironmentProperty("HOME", System.getProperty("user.home"));
                firefoxBinary.setEnvironmentProperty("DISPLAY",":1");
            }
            FirefoxProfile profile = new FirefoxProfile();
            if (crawlerProxy != null) {
                profile.setPreference("network.proxy.type", 1); // Manual proxy
                if(crawlerProxy.getType()!=null&&"socks5".equalsIgnoreCase(crawlerProxy.getType())){
                    profile.setPreference("network.proxy.socks", crawlerProxy.getIp());
                    profile.setPreference("network.proxy.socks_port", crawlerProxy.getPort());
                    profile.setPreference("network.proxy.socks_version", 5);
                }else{
                    // config
                    profile.setPreference("network.proxy.http", crawlerProxy.getIp());
                    profile.setPreference("network.proxy.http_port", crawlerProxy.getPort());
                    profile.setPreference("network.proxy.ssl", crawlerProxy.getIp());
                    profile.setPreference("network.proxy.ssl_port", crawlerProxy.getPort());
                }

            } else {
                profile.setPreference("network.proxy.type", 0); // no proxy config
            }
            e = new FirefoxDriver(firefoxBinary,profile,capabilities);

//                 ProfilesIni profile = new ProfilesIni();
//                FirefoxProfile myprofile = profile.getProfile("default");
//
//                if(myprofile==null){
//                    myprofile = new FirefoxProfile(new File(getDefaultFirefoxPorfilePath()));
//                }
//
//                if(proxyIpAndPort!=null){
//                    String[] proxy=proxyIpAndPort.split(":");
//                    myprofile.setPreference("network.proxy.type", 1); // Manual proxy config
//                    myprofile.setPreference("network.proxy.http", proxy[0]);
//                    myprofile.setPreference("network.proxy.http_port", new Integer(proxy[1]));
//                }
//                e = new FirefoxDriver(myprofile);


        }else if(type== Download.DownloadType.chrome_download){
            ChromeOptions options = new ChromeOptions();
            if("yarn".equalsIgnoreCase(System.getProperty("uumai.runmode"))){
                options.addArguments("-HOME="+System.getProperty("user.home"));
                options.addArguments("-DISPLAY=:1");
            }
            if (crawlerProxy != null) {
                 if(crawlerProxy.getType()!=null&&"socks5".equalsIgnoreCase(crawlerProxy.getType())){
                    options.addArguments("--proxy-server=socks5://" + crawlerProxy.getIp() + ":" + crawlerProxy.getPort());
                }else{
                     options.addArguments("--proxy-server=" + crawlerProxy.getIp() + ":" + crawlerProxy.getPort());
                }

            }
//            options.addArguments("--no-startup-window");
            //this parameters just didn't record log????,fuck
//             options.addArguments("--silent");
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);


            e = new ChromeDriver(capabilities);
        }else  if(type== Download.DownloadType.phantomjs_download){
            String userAgent = "Mozilla/5.0 (X11; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0";
            capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX + "userAgent", userAgent);
            if (crawlerProxy != null) {
                if(crawlerProxy.getType()!=null&&"socks5".equalsIgnoreCase(crawlerProxy.getType())){
                    ArrayList<String> cliArgsCap = new ArrayList<String>();
                    cliArgsCap.add("--proxy="+crawlerProxy.getIp()+":"+crawlerProxy.getPort());
                    cliArgsCap.add("--proxy-type=socks5");
                    capabilities.setCapability(
                            PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);

                }else{
                    ArrayList<String> cliArgsCap = new ArrayList<String>();
                    cliArgsCap.add("--proxy="+crawlerProxy.getIp()+":"+crawlerProxy.getPort());
                    cliArgsCap.add("--proxy-type=http");
                    capabilities.setCapability(
                            PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap);
                }

            }




            e = new PhantomJSDriver(capabilities);
        }else { //if(type== Download.DownloadType.jbrowser_download){
            Settings settings=  Settings.builder().
                    timezone(Timezone.ASIA_SHANGHAI)
                    //.ajaxResourceTimeout(60000)
                    //.ajaxWait(60000) .blockAds(true) .headless(true) .ignoreDialogs(true)
                    //.requestHeaders(RequestHeaders.CHROME) .ssl("trustanything") .userAgent(UserAgent.CHROME)
                    .build();
            if (crawlerProxy != null) {
                if (crawlerProxy.getType() != null && "socks5".equalsIgnoreCase(crawlerProxy.getType())) {
                    ProxyConfig proxyConfig = new ProxyConfig(ProxyConfig.Type.SOCKS, crawlerProxy.getIp(), crawlerProxy.getPort());
                    settings = settings.builder().proxy(proxyConfig).build();
                } else {
                    ProxyConfig proxyConfig = new ProxyConfig(ProxyConfig.Type.HTTP, crawlerProxy.getIp(),crawlerProxy.getPort());
                    settings = settings.builder().proxy(proxyConfig).build();
                }

            }
            e = new JBrowserDriver(settings);
//            e = new JBrowserDriver(capabilities);
        }

        return e;
    }
    private static String getFirefoxPorfilePath(String childpath,String profilename){
        String home = System.getProperty("user.home")+childpath;
//        System.out.println("user home:"+home);
        File file=new File(home+ "/.mozilla/firefox");
        if(file.exists()){
            File[] chileren=file.listFiles();
            for(File child:chileren){
                if(child.isDirectory()&&child.getName().endsWith("."+profilename)){
                    return child.getAbsolutePath();
                }
            }
        }
        return null;
    }
    public static void main(String[] args){
        String uumai_profile=getFirefoxPorfilePath("/uumai","uumai");
        if(uumai_profile==null)
            uumai_profile=getFirefoxPorfilePath("/uumai","default");
        if(uumai_profile==null)
            uumai_profile=getFirefoxPorfilePath("","uumai");
        if(uumai_profile==null)
            uumai_profile=getFirefoxPorfilePath("","default");

        System.out.println("uumai_profile:"+uumai_profile);

        FirefoxProfile profile = new FirefoxProfile(new File(uumai_profile));
        System.out.print(profile);
    }
}

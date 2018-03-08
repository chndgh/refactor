package com.company.project.server.download.httpdownload;


import com.company.project.server.CookieManager.CookieHelper;
import com.company.project.server.CookieManager.CrawlerCookie;
import com.company.project.server.download.Download;
import com.company.project.server.quartz.CrawlerResult;
import com.company.project.server.quartz.CrawlerTasker;

import javax.net.ssl.*;
import java.io.*;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.zip.GZIPInputStream;

/**
 * Created by kanxg on 14-12-18.
 */
public class HttpDownload implements Download {

    private CrawlerTasker tasker;

    private CookieManager manager;

    public HttpDownload(){
        CookieManager cm = new CookieManager();
        java.net.CookieHandler.setDefault(cm);
        manager=cm;
    }


    private void setCookie(HttpURLConnection urlConnection, List<CrawlerCookie> cookies){
        if(cookies!=null){
            String setcooki="";
            for(CrawlerCookie cookie:cookies){
                setcooki=setcooki + cookie.getName() +"=" + cookie.getValue()+";";
            }
//                setcooki=setcooki+";path=/;";
            if(setcooki.endsWith(";")){
                setcooki=setcooki.substring(0,setcooki.length()-1);
            }
//            System.out.println("cookie:" + setcooki);
            urlConnection.setRequestProperty("Cookie", setcooki);

        }
    }
    private void setIgnoreSSL(HttpURLConnection urlConnection) {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HostnameVerifier allHostsValid = new HostnameVerifier(){
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }

            };
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
            HttpsURLConnection  conn = (HttpsURLConnection)urlConnection;
            SSLSocketFactory sslSocketFactory = sc.getSocketFactory();
            conn.setSSLSocketFactory(sslSocketFactory);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

        private void setHeader(HttpURLConnection urlConnection){
        //set default value
        setDefaultHeader(urlConnection,"User-Agent",
                "Mozilla/5.0 (X11; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0");
//        urlConnection.setRequestProperty("Content-Type",
//                "application/x-www-form-urlencoded");
        //urlConnection.setRequestProperty("Transfer-Encoding", "chunked" );
//        urlConnection.setRequestProperty("Content-Length", "200");
        //urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
//        urlConnection.setRequestProperty("Content-Type",
//                "application/json; charset=utf-8");
        setDefaultHeader(urlConnection, "Content-Type",
                "application/x-www-form-urlencoded; charset=UTF-8");

//         setDefaultHeader(urlConnection, "Content-Type",
//                        "application/json; charset=UTF-8");
        setDefaultHeader(urlConnection,"Accept-Encoding", "gzip,deflate");
//        setDefaultHeader(urlConnection,"Referer", "google.com");

        urlConnection.setConnectTimeout(5000);
        urlConnection.setReadTimeout(5000*2);

        if(this.tasker.getRequestHeaderList()==null ) return;
        if(this.tasker.getRequestHeaderList().size()==0 ) return;

        for(String requestHeaderStr: this.tasker.getRequestHeaderList()){
            int splitpos=requestHeaderStr.indexOf(":");
            if(splitpos>0){
                String header=requestHeaderStr.substring(0,splitpos);
                String value=requestHeaderStr.substring(splitpos+1,requestHeaderStr.length());
                if(!"null".equalsIgnoreCase(value)){
                    if("setConnectTimeout".equalsIgnoreCase(header)) {
                        urlConnection.setConnectTimeout(new Integer(value));

                    }else if("setReadTimeout".equalsIgnoreCase(header)) {
                        urlConnection.setReadTimeout(new Integer(value));

                    }else{
                        urlConnection.setRequestProperty(header, value);
                    }
                }
            }
//            String[] requestHeader=requestHeaderStr.split(":");
//            if(requestHeader==null||requestHeader.length!=2) continue;
//            if(!"null".equalsIgnoreCase(requestHeader[1])){
//                if("setConnectTimeout".equalsIgnoreCase(requestHeader[0])) {
//                    urlConnection.setConnectTimeout(new Integer(requestHeader[1]));
//
//                }else if("setReadTimeout".equalsIgnoreCase(requestHeader[0])) {
//                    urlConnection.setReadTimeout(new Integer(requestHeader[1]));
//
//                }else{
//                    urlConnection.setRequestProperty(requestHeader[0], requestHeader[1]);
//                }
//            }
        }
    }

    /**
     * if cusomer not set this header, use default value
     * if customer set this header:
     *      if value==null,   clean default value;
     *      else  set value from customer
     * @param urlConnection
     * @param header
     * @param value
     */

    private void setDefaultHeader(HttpURLConnection urlConnection,String header,String value){
        boolean customersetthisheader=false;
        if(this.tasker.getRequestHeaderList()!=null && this.tasker.getRequestHeaderList().size()!=0  ){
            for(String requestHeaderStr: this.tasker.getRequestHeaderList()) {
                String[] requestHeader = requestHeaderStr.split(":");
                if(requestHeader==null||requestHeader.length!=2) continue;
                     if(requestHeader[0].equalsIgnoreCase(header)) {
                         customersetthisheader = true;
                         break;
                     }
            }
        }
        if(!customersetthisheader) {
            urlConnection.addRequestProperty(header, value);
        }
    }
    private CrawlerResult download(String urlStr, List<CrawlerCookie> cookies, Proxy proxy, String incomingencode, boolean follingRedirect) throws Exception {

        URL url = new URL(urlStr);
        HttpURLConnection urlConnection ;
        if(proxy==null){
            urlConnection=(HttpURLConnection) url
                    .openConnection();
        }else{
            urlConnection=(HttpURLConnection) url
                    .openConnection(proxy);
        }
//        urlConnection.setDoInput(true);
//
//        urlConnection.setDoOutput(true);

        setHeader(urlConnection);

        if(urlStr.startsWith("https")){
            this.setIgnoreSSL(urlConnection);
        }


//        urlConnection.setInstanceFollowRedirects(true);
//        HttpURLConnection.setFollowRedirects(true);

        setCookie(urlConnection,cookies);
//        urlConnection.setRequestProperty("Cookie", "cz-book=4825|4446|8439|843972656; cz-book-think-0=94ca4514; cz-book-think-2=312bf12d; cz-book-think-3=312bf12d57ddeb11; language=zh_CN; JSESSIONID=ada4c8f2-a5c4-44ac-a684-3b9f7cc26a56");
        urlConnection.connect();

        int return_code=urlConnection.getResponseCode();
        //System.out.println("return_code"+return_code);
        CrawlerResult crawlerResult=new CrawlerResult();
        crawlerResult.setReturncode(return_code);
        CookieHelper cookieHelper=new CookieHelper();
//        crawlerResult.setCookies(cookies);
//        crawlerResult.addNewCookies(cookieHelper.parseCookies(urlConnection));
        crawlerResult.addNewCookies(cookieHelper.parseCookies(manager.getCookieStore().getCookies()));

        if (return_code >= 300) {
            System.out.println("failed: url:" + url);
            if(return_code== 301 ||return_code==302){
                String reurl=urlConnection.getHeaderField("Location");
                System.out.println("redirect url"+urlStr  +"  >>> " +reurl );
                if(follingRedirect){
                    return this.download(reurl,crawlerResult.getCookies(),proxy,incomingencode,false);
//                URL u = new URL(urlConnection.getHeaderField("Location"));
//                if(proxy==null){
//                    urlConnection=(HttpURLConnection) u
//                            .openConnection();
//                }else{
//                    urlConnection=(HttpURLConnection) u
//                            .openConnection(proxy);
//                }
//                String redcookies=urlConnection.getRequestProperty("Cookie");
//                System.out.println("redcookies:" + redcookies);
                }else{
                    return crawlerResult;
                }


            }else {

                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    byte[] buf = new byte[4096];
                     InputStream es = urlConnection.getErrorStream();
                    int ret = 0;
                    // read the response body
                    while ((ret = es.read(buf)) > 0) {
                        os.write(buf, 0, ret);
                    }
                    // close the errorstream
                    es.close();

                    System.out.println("failed: return_code: " + return_code);

//                    System.out.println("failed: return_code" + return_code+",Error:"+ new String(os.toByteArray()));
                    throw new Exception("Error when download:"+return_code +",Error:"+ new String(os.toByteArray()));


            }
        }

        //InputStream is= urlConnection.getInputStream();

        String encoding=urlConnection.getContentType();
        if(encoding!=null){
            if(encoding.indexOf("charset=")==-1){
                encoding=null;
            }else{
                encoding=encoding.substring(encoding.indexOf("charset=")+8).trim();
            }
        }



        InputStream is=null;
        if ("gzip".equals(urlConnection.getContentEncoding())) {
            is = new GZIPInputStream(
                    urlConnection.getInputStream());
        }else{
            is=urlConnection.getInputStream();
        }

        //is = urlConnection.getInputStream();
        BufferedReader reader = null;
        if(incomingencode==null){
            if (encoding==null){
                reader=new BufferedReader(
                        new InputStreamReader(is,"utf-8"));
            }else{
                reader=new BufferedReader(
                        new InputStreamReader(is,encoding));
            }
        }else{
            reader=new BufferedReader(
                    new InputStreamReader(is,incomingencode));
        }


        String s;
        StringBuilder result = new StringBuilder();
        while (((s = reader.readLine()) != null)) {
            result.append(s);
        }

        reader.close();

        //urlConnection.disconnect();

        //System.out.println("result= " + result.toString());
        //System.out.println("cookie:"+ parseCookies(urlConnection));

        crawlerResult.setRawText(result.toString());
        return crawlerResult;

//        Html html =new Html(result.toString());
//
//        String title =html.xpath("//h2[@class='name']/text()").toString();
//
//        is.close();
//        return title;

//        return Jsoup.clean(result.toString(), Whitelist.basic());

    }


    private CrawlerResult doPost(String Requestmethod,String urlStr,String postdata,List<CrawlerCookie> cookies, Proxy proxy,String incomingencode,boolean follingRedirect)  throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection urlConnection ;
        if(proxy==null){
            urlConnection=(HttpURLConnection) url
                    .openConnection();
        }else{
            urlConnection=(HttpURLConnection) url
                    .openConnection(proxy);
        }
//        urlConnection.setDoInput(true);
//
//        urlConnection.setDoOutput(true);
//        urlConnection.setRequestProperty("Connection", "Keep-Alive");
//
//        urlConnection.setRequestProperty("User-Agent",
//                "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT)");
//        urlConnection.setRequestProperty("Content-Type",
//                "application/x-www-form-urlencoded");
//        urlConnection.setRequestProperty("Transfer-Encoding", "chunked" );
//
//        urlConnection.setRequestProperty("Content-Length", "200");
        setHeader(urlConnection);
        setCookie(urlConnection,cookies);

        urlConnection.setRequestMethod(Requestmethod);// 设置请求方法为post , put
        urlConnection.setDoOutput(true);// 设置此方法,允许向服务器输出内容


         // post请求的参数
         // 获得一个输出流,向服务器写数据,默认情况下,系统不允许向服务器输出内容
        OutputStream out = urlConnection.getOutputStream();// 获得一个输出流,向服务器写数据
        out.write(postdata.getBytes("UTF-8"));
        out.flush();
        out.close();


        int return_code=urlConnection.getResponseCode();
        //System.out.println("return_code"+return_code);

//        if(return_code!=200){
//            throw new Exception("Error when parse");
//        }
        CookieHelper cookieHelper =new CookieHelper();
        CrawlerResult crawlerResult=new CrawlerResult();
        crawlerResult.setReturncode(return_code);
//        crawlerResult.setCookies(cookies);
//        crawlerResult.addNewCookies(cookieHelper.parseCookies(urlConnection));
        crawlerResult.addNewCookies(cookieHelper.parseCookies(manager.getCookieStore().getCookies()));


        if (return_code >= 300) {
            System.out.println("failed: url:" + url);
            if(return_code== 301 ||return_code==302){
                String reurl=urlConnection.getHeaderField("Location");
                System.out.println("redirect url"+urlStr  +"  >>> " +reurl );
                if(follingRedirect){
                    return this.download(reurl,crawlerResult.getCookies(),proxy,incomingencode,true);
//                URL u = new URL(urlConnection.getHeaderField("Location"));
//                if(proxy==null){
//                    urlConnection=(HttpURLConnection) u
//                            .openConnection();
//                }else{
//                    urlConnection=(HttpURLConnection) u
//                            .openConnection(proxy);
//                }
//                String redcookies=urlConnection.getRequestProperty("Cookie");
//                System.out.println("redcookies:" + redcookies);
                }else{
                    return crawlerResult;
                }


            }else {
                try {
                    ByteArrayOutputStream os = new ByteArrayOutputStream();
                    byte[] buf = new byte[4096];
                    InputStream es = urlConnection.getErrorStream();
                    int ret = 0;
                    // read the response body
                    while ((ret = es.read(buf)) > 0) {
                        os.write(buf, 0, ret);
                    }
                    // close the errorstream
                    es.close();

                    System.out.println("failed: return_code: " + return_code);

//                    System.out.println("failed: return_code" + return_code+",Error:"+ new String(os.toByteArray()));
                    throw new Exception("Error when download:"+return_code +",Error:"+ new String(os.toByteArray()));

                } catch(IOException ex) {
                    throw new Exception("Error when download:"+return_code+",Error:"+ex.getMessage());
                }
            }
        }




        String encoding=urlConnection.getContentType();
        if(encoding!=null){
            if(encoding.indexOf("charset=")==-1){
                encoding=null;
            }else{
                encoding=encoding.substring(encoding.indexOf("charset=")+8).trim();
            }
        }

        InputStream is=null;
        if ("gzip".equals(urlConnection.getContentEncoding())) {
            is = new GZIPInputStream(
                    urlConnection.getInputStream());
        }else{
            is=urlConnection.getInputStream();
        }

        BufferedReader reader = null;
        if(incomingencode==null){
            if (encoding==null){
                reader=new BufferedReader(
                        new InputStreamReader(is,"utf-8"));
            }else{
                reader=new BufferedReader(
                        new InputStreamReader(is,encoding));
            }
        }else{
            reader=new BufferedReader(
                    new InputStreamReader(is,incomingencode));
        }


        String s;
        StringBuilder result = new StringBuilder();
        while (((s = reader.readLine()) != null)) {
            result.append(s);
        }

        //System.out.println("result= " + result.toString());
        crawlerResult.setRawText(result.toString());
        return crawlerResult;

//        Html html =new Html(result.toString());
//
//        String title =html.xpath("//h2[@class='name']/text()").toString();
//
//        is.close();
//        return title;
    }

    @Override
    public CrawlerResult download(CrawlerTasker tasker) throws Exception {
        this.tasker=tasker;
        if(tasker.getRequestmethod().equalsIgnoreCase("POST")||tasker.getRequestmethod().equalsIgnoreCase("PUT")){
            if(tasker.getProxy()!=null){

                return doPost(tasker.getRequestmethod(),tasker.getUrl(), tasker.getPostdata(),tasker.getCookies(),tasker.getProxy().getproxy(),tasker.getEncoding(),tasker.isFollingRedirect());
            }
            return doPost(tasker.getRequestmethod(),tasker.getUrl(),tasker.getPostdata(), tasker.getCookies(),null,tasker.getEncoding(), tasker.isFollingRedirect());

        }else{
            if(tasker.getProxy()!=null){

                return download(tasker.getUrl(),tasker.getCookies(),tasker.getProxy().getproxy(),tasker.getEncoding(),tasker.isFollingRedirect());
            }
            return download(tasker.getUrl(),tasker.getCookies(),null,tasker.getEncoding(),tasker.isFollingRedirect());
        }

    }
}

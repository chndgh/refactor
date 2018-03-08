package com.company.project.server.quartz;

import com.google.gson.JsonObject;
import com.uumai.server.quartz.pipeline.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: rock Date: 3/19/15 Time: 5:53 PM To change
 * this template use File | Settings | File Templates.
 */
public class QuartzCrawlerWorker extends MultiCrawlerWorker {

    // public static ExcelFileUtil excelFileUtil=new
    // ExcelFileUtil("/home/rock/quartz.xls");

    static {
        // excelFileUtil.writeLine("screenName","symbol","name","time","price","saleFlag","count");
    }

    public QuartzCrawlerWorker(CrawlerTasker tasker) {

        super(tasker);
    }

    @Override
    protected void download() throws Exception {
        super.download();
        // QuartzCrawlerTasker quartztasker = (QuartzCrawlerTasker) tasker;
        // if(DownloadType.chrome_download.equals(quartztasker.getDownloadType())
        // ||DownloadType.firefox_download.equals(quartztasker.getDownloadType())
        // ||DownloadType.phantomjs_download.equals(quartztasker.getDownloadType())){
        // if(quartztasker.getSeleniumActionses()!=null){
        // this.result=new QuartzSeleniumDownloader().download(quartztasker);
        // }else{
        // super.download();
        // }
        //
        // }else{
        // super.download();
        // }
    }

    // @Override
    // protected Download getDownloadInstantce() throws Exception {
    // QuartzCrawlerTasker quartztasker = (QuartzCrawlerTasker) tasker;
    //
    // return super.getDownloadInstantce();
    // }

    @Override
    protected void pipeline() throws Exception {

        QuartzCrawlerTasker quartztasker = (QuartzCrawlerTasker) tasker;

        // tasker.setRawText(Jsoup.clean(tasker.getRawText(), Whitelist.basic()));
        JsonParseHelper jsonParseHelper = new JsonParseHelper(quartztasker, result);

        List<JsonObject> list = jsonParseHelper.parse();

		 for (JsonObject obj : list) {
             // helper.store(obj.toString(), quartztasker.getStoreTableName());
             System.out.println(obj.toString());
         }

        if (quartztasker.getStoreTableName().equals("tproxy")) {
            // save into proxy table
            new MimvpPipeline().saveresult(list);
        } else if (quartztasker.getStoreTableName().equals("t_proxy_verfiy")) {
            new ProxyVerifyPipeline().saveresult(list);
        } else if (quartztasker.getStoreTableName().equals("taobao_search")) {
            new TaobaoSearchPipeline().saveresult(list);
        } else if (quartztasker.getStoreTableName().equals("taobao_product")) {
            new TaobaoProductPipeline().saveresult(list);
        } else if (quartztasker.getStoreTableName().equals("taobao_shop")) {
            new TaobaoShopPipeline().saveresult(list);
        } else if (quartztasker.getStoreTableName().equals("taobao_product_shoucang")) {
            new TaobaoProductShoucangPipeline().saveresult(list);
        } else if (quartztasker.getStoreTableName().equals("taobao_product_pinlun")) {
            new TaobaoProductPinlunPipeline().saveresult(list);
        } else if (quartztasker.getStoreTableName().equals("taobao_product_xiaoliang")) {
            new TaobaoProductXiaoliangPipeline().saveresult(list);
        }

    }

    @Override
    protected void failHander() {
        QuartzCrawlerTasker quartztasker = (QuartzCrawlerTasker) tasker;
        if (quartztasker.getStoreTableName().equals("tproxy")) {
            // save into proxy table
//			new MimvpPipeline().saveresult(list);
        } else if (quartztasker.getStoreTableName().equals("t_proxy_verfiy")) {
            new ProxyVerifyPipeline().failHander(quartztasker);
        } else if (quartztasker.getStoreTableName().equals("taobao_search")) {
//			new TaobaoSearchPipeline().saveresult(list);
        } else if (quartztasker.getStoreTableName().equals("taobao_product")) {
//			new TaobaoProductPipeline().saveresult(list);
        } else if (quartztasker.getStoreTableName().equals("taobao_product_shoucang")) {
//			new TaobaoProductShoucangPipeline().saveresult(list);
        } else if (quartztasker.getStoreTableName().equals("taobao_product_pinlun")) {
//			new TaobaoProductPinlunPipeline().saveresult(list);
        } else if (quartztasker.getStoreTableName().equals("taobao_product_xiaoliang")) {
//			new TaobaoProductXiaoliangPipeline().saveresult(list);
        }
    }
}
package com.company.project.service.impl.search;



import com.company.project.client.quartz.taobao.cookie.CookieHelper;
import com.company.project.client.util.UumaiTime;
import com.company.project.dao.TSearchCategoryMapper;
import com.company.project.dao.TSearchMapper;
import com.company.project.model.TSearch;
import com.company.project.model.TSearchCategory;
import com.company.project.server.quartz.QuartzCrawlerTasker;
import com.company.project.service.AbstractAppMaster;
import com.company.project.service.TSearchService;
import com.company.project.service.impl.TProxyVerifyServiceImpl;
import com.company.project.service.impl.TSearchCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TSearchServiceImpl extends AbstractAppMaster {

    @Autowired
    TProxyVerifyServiceImpl proxyVerifyService;

    @Autowired
    TSearchCategoryServiceImpl searchCategoryService;

    @Autowired
    TSearchCategoryMapper searchCategoryMapper;

    @Autowired
    TSearchMapper searchMapper;

    private int retrytime = 10;
    public String cateid;

    @Override
    public void dobusiness() throws Exception {
        System.out.println("start to do category:" + cateid);
        getonecategory(cateid);
        System.out.println("end to do category:" + cateid);

//        String cateid = "50002634";
//        getonecategory(cateid);

    }


    private void getonecategory(String cateid) throws Exception {
        String link = "https://s.taobao.com/search?sort=sale-desc&style=list&cat=" + cateid;

        String taskerserie;
        int page = -1;
        while (true) {
            taskerserie = cateid + new UumaiTime().getNowString();
            dotask(link, cateid, 1, true, taskerserie);
            waittaskfinished();
            cleancategory(cateid, true);
            page = getpagecount(link, cateid);
            if (page > 0) break;
        }

        for (int j = 1; j <= 10; j++) {
            int total = gettotalcount(link, cateid);
            if (total >= page) break;
            // means it's not first time to get first 100 page.
//            if (j == 1 && total > 0) break;
            taskerserie = cateid + new UumaiTime().getNowString();
            for (int i = 2; i <= page; i++) {
                dotask(link, cateid, i, true, taskerserie);
            }
            waittaskfinished();
            cleancategory(cateid, true);
        }

//        for test , we only get all for category 大家电.
//        if (!cateid.equals("50035182"))
//            return;

        if (page == 100)

        {
            List<Double> topPrices = getTopSellPrice(cateid);
            Collections.sort(topPrices);
            for (int j = 1; j <= retrytime; j++) {
                getotherspageByPrice(cateid, topPrices,0);
                cleancategory(cateid, true);
            }
        }

    }

    private void cleancategory(String cateid, boolean onlyerror) throws Exception {
        Condition condition = new Condition(TSearchCategory.class);

        if (onlyerror) {
            condition.createCriteria().andEqualTo("categoryid",cateid).andLessThan("totalcount",0);
        } else {
            condition.createCriteria().andEqualTo("categoryid",cateid);
        }
        searchCategoryMapper.deleteByCondition(condition);

    }

    private void cleanTsearch(String cateid) throws Exception {
        Condition condition = new Condition(TSearch.class);
        condition.createCriteria().andEqualTo("categoryid",cateid);
        searchMapper.deleteByCondition(condition);
    }

    private int gettotalcount(String link, String cateid) {
        Condition condition = new Condition(TSearchCategory.class);
        condition.createCriteria().andEqualTo("categoryid",cateid).andEqualTo("url",link);
        return new Long(searchCategoryMapper.selectCountByCondition(condition)).intValue();
    }

    private int getDivide(String link, String cateid){
        Condition condition = new Condition(TSearchCategory.class);
        condition.createCriteria().andEqualTo("categoryid",cateid).andEqualTo("url",link);
        List<TSearchCategory> list = searchCategoryMapper.selectByCondition(condition);
        if (list != null && list.size() != 0) {
            int totalcount = list.get(0).getTotalcount();
            if (totalcount>4400){
                if (totalcount<50000){
                    return totalcount/4400;
                }else {
                    return 20;
                }
            }
        }
        return -1;
    }
    private int getpagecount(String link, String cateid) {
        Condition condition = new Condition(TSearchCategory.class);
        condition.createCriteria().andEqualTo("categoryid",cateid).andEqualTo("url",link);
        List<TSearchCategory> list = searchCategoryMapper.selectByCondition(condition);
        int total = -1;
        if (list != null && list.size() != 0) {
            int totalccount = list.get(0).getTotalcount();
            if (totalccount > 4400) return 100;
            try {
                total = totalccount / 44;
                if (total < 50 && (totalccount % 44 != 0)) {
                    total = total + 1;
                }
            } catch (Exception e) {

            }
        }
        return total;
    }

    private List<Double> getTopSellPrice(String categoryId) throws Exception {

        Condition condition = new Condition(TSearch.class);
        condition.createCriteria().andEqualTo("categoryid",categoryId).andEqualTo("basesearch",1);
        List<TSearch> topSearchResult = searchMapper.selectByCondition(condition);
        //must filter with size < 100
        return topSearchResult.stream().map(item -> Double.parseDouble(item.getPrice())).distinct().collect(Collectors.toList());


    }

    private void loopBetweenPrice(Double min, Double max, String categoryId, boolean isfistpage, String taskerseries,int divide) throws Exception {
        if (divide==0){
            loopPrice(min,max,categoryId,isfistpage,taskerseries,0);
        }else{
            Double tempInterval = (max-min)/divide;
            Double interval = (double)Math.round(tempInterval*100)/100;
            for (int i=0;i<=divide;i++){
                Double subMin = min + interval * i;
                Double subMax = min + interval * (i+1);
                loopPrice(subMin,subMax,categoryId,isfistpage,taskerseries,1000);
            }
        }
    }

    private void loopPrice(Double min, Double max, String categoryId, boolean isfistpage, String taskerserie,int divide) throws Exception {
        String link = "https://s.taobao.com/search?sort=sale-desc&style=list&cat=" + categoryId +
                "&filter=reserve_price[" + min + "," + max + "]";
        if (isfistpage) {
            dotask(link, categoryId, 1, false, taskerserie);
        } else {
            int page = getpagecount(link, categoryId);
            if (page<2)return;
            if (page == 100 && divide == 0) {
                int division = getDivide(link, categoryId);
                getotherspageByPrice(categoryId, Arrays.asList(min, max), division);
            }
            for (int i = 2; i <= page; i++) {
                dotask(link, categoryId, i, false, taskerserie);
            }
        }


    }

    private void getotherspageByPrice(String categoryId, List<Double> topPrices,int divide) throws Exception {
        //loop first page
        String taskerserie = categoryId + new UumaiTime().getNowString();
        for (int i = 0; i < topPrices.size() - 1; i++) {
//            if (i == 0) {
//                Double firstMin = 0.00;
//                Double firstMax = topPrices.get(0);
//                loopBetweenPrice(firstMin, firstMax, categoryId, true, taskerserie);
//            }
            Double min = topPrices.get(i);
            Double max = topPrices.get(i + 1);
            loopBetweenPrice(min, max, categoryId, true, taskerserie,divide);
//            if (i == topPrices.size() - 1) {
//                Double lastMin = topPrices.get(i);
//                loopBetweenPrice(lastMin, null, categoryId, true, taskerserie);
//            }
        }
        waittaskfinished();

        //loop other pages
        taskerserie = categoryId + new UumaiTime().getNowString();
        for (int i = 0; i < topPrices.size() - 1; i++) {
//            if (i == 0) {
//                Double firstMin = 0.00;
//                Double firstMax = topPrices.get(0);
//                loopBetweenPrice(firstMin, firstMax, categoryId, false, taskerserie);
//            }
            Double min = topPrices.get(i);
            Double max = topPrices.get(i + 1);
            loopBetweenPrice(min, max, categoryId, false, taskerserie,divide);
//            if (i == topPrices.size() - 1) {
//                Double lastMin = topPrices.get(i);
//                loopBetweenPrice(lastMin, null, categoryId, false, taskerserie);
//            }
        }
        waittaskfinished();
    }


    private boolean checkexist(String link, String cateid, int page) throws Exception {
        Condition condition = new Condition(TSearchCategory.class);
        condition.createCriteria().andEqualTo("categoryid",cateid).andEqualTo("url",link).andEqualTo("page",page);
        int count = searchCategoryMapper.selectCountByCondition(condition);
        if (count == 0) {
            return false;
        }
        return true;
    }

    private void dotask(String link, String cateid, int page, boolean baseSearch, String taskerserie) throws Exception {

        if (checkexist(link, cateid, page))
            return;

        String url = link + "&s=" + (page - 1) * 44;


        QuartzCrawlerTasker tasker = new QuartzCrawlerTasker();
        tasker.setCookies(CookieHelper.getTmallCookie());

        // tasker.setUrl("http://data.eastmoney.com/zjlx/600307.html");
        tasker.setUrl(url);
        tasker.setTaskerOwner("rock");
        tasker.setTaskerName("uumai_quartz_taobao_cate");
        // tasker.setDownloadType(DownloadType.selenium_download);
        tasker.setTaskerSeries(taskerserie);
        tasker.setStoreTableName("taobao_search");
//        tasker.addResultItem("url", url);
        tasker.addResultItem("link", link);
        tasker.addResultItem("cateid", cateid);
//        tasker.addResultItem("catename", catename);
//        tasker.addResultItem("type", type);    //1 total ,2 by price
        tasker.addResultItem("page", page);
        tasker.addResultItem("baseSearch", baseSearch ? 1 : 0);

        tasker.setProxy(proxyVerifyService.getone());
        tasker.addRegexExpress("json", "g_page_config = (.*);").asSource("json").setNotOutput();

        tasker.addJsonpath("pageName", "$..pageName").fromsource("json");

        tasker.addJsonpath_all("url", "$..itemlist.data.auctions.detail_url").fromsource("json");
        tasker.addJsonpath_all("nid", "$..itemlist.data.auctions.nid").fromsource("json");
        tasker.addJsonpath_all("category", "$..itemlist.data.auctions.category").fromsource("json");
        tasker.addJsonpath_all("title", "$..itemlist.data.auctions.title").fromsource("json");
        tasker.addJsonpath_all("view_price", "$..itemlist.data.auctions.view_price").fromsource("json");
        tasker.addJsonpath_all("view_fee", "$..itemlist.data.auctions.view_fee").fromsource("json");

        tasker.addJsonpath_all("item_loc", "$..itemlist.data.auctions.item_loc").fromsource("json");
        tasker.addJsonpath_all("reserve_price", "$..itemlist.data.auctions.reserve_price").fromsource("json");
        tasker.addJsonpath_all("view_sales", "$..itemlist.data.auctions.view_sales").fromsource("json");
        tasker.addJsonpath_all("comment_count", "$..itemlist.data.auctions.comment_count").fromsource("json");
        tasker.addJsonpath_all("user_id", "$..itemlist.data.auctions.user_id").fromsource("json");
        tasker.addJsonpath_all("nick", "$..itemlist.data.auctions.nick").fromsource("json");

        //added to judge whether it is tmall
        tasker.addJsonpath_all("isTmall", "$..itemlist.data.auctions.shopcard.isTmall").fromsource("json");
        for (int i = 0; i < 44; i++) {
            tasker.addJsonpath_all("innerText" + i + "_", "$..itemlist.data.auctions[" + i + "].icon.innerText").fromsource("json");
        }

        tasker.addJsonpath_all("sellerCredit", "$..itemlist.data.auctions.shopcard.sellerCredit").fromsource("json");
        tasker.addJsonpath_all("totalRate", "$..itemlist.data.auctions.shopcard.totalRate").fromsource("json");

        //picture url
        tasker.addJsonpath_all("pic_url", "$..itemlist.data.auctions.pic_url").fromsource("json");
        //added to judge platform,  since icon[1] not work,so commented @TODO 1
        //tasker.addJsonpath_all("icon_type","$..itemlist.data.auctions.icon[1].dom_class").fromsource("json");

        tasker.addJsonpath("totalPage", "$..pager.data.totalPage").fromsource("json");
        tasker.addJsonpath("totalCount", "$..pager.data.totalCount").fromsource("json");

        for (int i = 0; i < 44; i++) {
            tasker.addJsonpath_all("html" + i + "_", "$..auctions[" + i + "].icon.html").fromsource("json");
        }

        putDistributeTask(tasker);
    }

}

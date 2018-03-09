package com.company.project.service.proxy;

import com.company.project.client.util.UumaiTime;
import com.company.project.dao.TProxyMapper;
import com.company.project.server.quartz.QuartzCrawlerTasker;
import com.company.project.service.AbstractAppMaster;
import com.company.project.service.impl.TSystemlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MimvpKuaidailiTasker extends AbstractAppMaster {

    @Autowired
    TSystemlogServiceImpl systemlogService;

    @Autowired
    TProxyMapper tProxyMapper;

    @Override
    public void dobusiness() throws Exception {
        systemlogService.clean("uumai_quartz_daili_list_mimvp");

        String taskerserie = new UumaiTime().getNowString();
        cleanold();
        getall("http://proxy.mimvp.com/api/fetch.php?orderid=860171031112407201&num=2000&result_fields=1,2,3,5,6,9&result_format=json",
                taskerserie);

        waittaskfinished();
    }

    private void cleanold() {
        tProxyMapper.deleteByCondition(null);
    }


    private void getall(String url, String taskerserie) throws Exception {
        QuartzCrawlerTasker tasker = new QuartzCrawlerTasker();
        tasker.setUrl(url);
        tasker.setTaskerOwner("rock");
        tasker.setTaskerName("uumai_quartz_daili_list_mimvp");
        tasker.setTaskerSeries(taskerserie);
        tasker.setStoreTableName("tproxy");
        tasker.addResultItem("from", "mimvp");
        tasker.addJsonpath("", "*");
        putDistributeTask(tasker);
    }

}

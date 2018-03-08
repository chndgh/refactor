package com.company.project.service.proxy;



import com.company.project.client.AbstractAppMaster;
import com.company.project.client.quartz.taobao.cookie.CookieHelper;
import com.company.project.client.util.UumaiTime;
import com.company.project.dao.TProxyMapper;
import com.company.project.dao.TProxyVerifyMapper;
import com.company.project.model.TProxy;
import com.company.project.server.download.CrawlerProxy;
import com.company.project.server.quartz.QuartzCrawlerTasker;
import com.company.project.service.impl.TSystemlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CheckProxyValidateTasker extends AbstractAppMaster { // QuartzLocalAppMaster{ //AbstractAppMaster {
	static final String url = "https://s.taobao.com/search?sort=sale-desc&style=list&cat=50002634&s=0";
	static final String table = "t_proxy_verfiy";
	static final String verify_test_value = "100";

	@Autowired
	TProxyMapper proxyMapper;

	@Autowired
	TSystemlogServiceImpl systemlogService;

	@Autowired
	TProxyVerifyMapper proxyVerifyMapper;

	@Override
	public void dobusiness() throws Exception {
		systemlogService.clean("uumai_daili_list_test");
		cleanold();
		UumaiTime uumaiTime = new UumaiTime();
		String taskerserie = uumaiTime.getNowString();
		List<TProxy> result = proxyMapper.selectAll();
		for (TProxy tp : result) {
			getall(url, tp.getIp(), tp.getPort().toString(), tp.getType(), taskerserie);
		}
		waittaskfinished();
	}

	private void cleanold() {
		proxyVerifyMapper.deleteByCondition(null);
	}
	private void getall(String url, String ip, String port, String http_type, String taskerserie) throws Exception {
		QuartzCrawlerTasker tasker = new QuartzCrawlerTasker();
		// tasker.setCookies(cookie);
		tasker.setUrl(url);
		if(http_type.startsWith("Socks")){
			tasker.setProxy(new CrawlerProxy(ip, new Integer(port),"socks"));
		}else{
			tasker.setProxy(new CrawlerProxy(ip, new Integer(port)));
		}

		tasker.setTaskerOwner("rock");
		tasker.setTaskerName("uumai_daili_list_test");
		// tasker.addRequestHeader("setConnectTimeout", 5000*2+"");
		// tasker.addRequestHeader("setReadTimeout", 5000*4+"");
		tasker.setCookies(CookieHelper.getTaobaoCookie());

		// tasker.setDownloadType(DownloadType.java_download);
		tasker.setTaskerSeries(taskerserie);
		tasker.setStoreTableName(table);
		tasker.addResultItem("ip", ip);
		tasker.addResultItem("port", port);
		tasker.addResultItem("http_type", http_type);
		tasker.addResultItem("verify_test_value", verify_test_value);

		tasker.addRegexExpress("json", "g_page_config = (.*);").asSource("json").setNotOutput();
		tasker.addJsonpath("xpath", "$..pager.data.totalPage").fromsource("json");

		// tasker.addXpath("xpath", xpath);

		putDistributeTask(tasker);
	}
}
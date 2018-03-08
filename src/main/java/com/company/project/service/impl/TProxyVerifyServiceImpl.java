package com.company.project.service.impl;

import com.company.project.dao.TProxyVerifyMapper;
import com.company.project.model.TProxyVerify;
import com.company.project.server.download.CrawlerProxy;
import com.company.project.service.TProxyVerifyService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/03/08.
 */
@Service
@Transactional
public class TProxyVerifyServiceImpl extends AbstractService<TProxyVerify> implements TProxyVerifyService {
    @Resource
    private TProxyVerifyMapper tProxyVerifyMapper;

    private int index = 0;

    private List<CrawlerProxy> proxylist = new ArrayList<CrawlerProxy>();




    public CrawlerProxy getone() {
        if (proxylist.size() == 0)
            reloadall();
        if (index >= proxylist.size()) {
            this.reloadall();
            index = 0;
        }
        CrawlerProxy ip = proxylist.get(index);
        index = index + 1;
        return ip;
    }

    private void reloadall() {
//        System.out.println("reload proxy...");
        while (true) {
            loadall();
            if (proxylist.size() != 0)
                break;
            System.out.println("no proxy could use,sleep 5 seconds and retry...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
        }
    }

    private void loadall() {
        proxylist.clear();
        List<TProxyVerify> proxyVerifyList = tProxyVerifyMapper.selectByCondition(new Condition(TProxyVerify.class).createCriteria().andEqualTo("pass",1));

        for (TProxyVerify item : proxyVerifyList) {
            if(item.getType().startsWith("Socks")){
                proxylist.add(new CrawlerProxy(item.getIp(), new Integer(item.getPort()),"socks"));
            }else{
                proxylist.add(new CrawlerProxy(item.getIp(), new Integer(item.getPort())));
            }
        }
    }

}

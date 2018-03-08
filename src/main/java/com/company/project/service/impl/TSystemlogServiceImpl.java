package com.company.project.service.impl;

import com.company.project.dao.TSearchMapper;
import com.company.project.dao.TSystemlogMapper;
import com.company.project.model.TSystemlog;
import com.company.project.service.TSystemlogService;
import com.company.project.core.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by CodeGenerator on 2018/03/08.
 */
@Service
@Transactional
public class TSystemlogServiceImpl extends AbstractService<TSystemlog> implements TSystemlogService {
    @Resource
    private TSystemlogMapper tSystemlogMapper;


    public void clean(String taskerName) {
        TSystemlog log = findBy("taskerName",taskerName);
        deleteById(log.getId());
    }

    public void clean(String taskerOwner, String taskerName, String taskerSeries) {
        Condition condition = new Condition(TSystemlog.class);
        condition.createCriteria().andEqualTo("taskerowner",taskerOwner)
                .andEqualTo("taskername",taskerName).andEqualTo("taskerseries",taskerSeries);
        tSystemlogMapper.deleteByCondition(condition);
    }


    public void createLog(TSystemlog Systemlog) {
        tSystemlogMapper.insert(Systemlog);
    }

    public long getLogs(String taskerOwner, String taskerName, String taskerSeries) {

        return this.getLogs(taskerOwner, taskerName, taskerSeries, null);

    }

    public long getLogs(String taskerOwner, String taskerName, String taskerSeries, String result) {
        Condition condition = new Condition(TSystemlog.class);
        condition.createCriteria().andEqualTo("taskername",taskerName).andEqualTo("owner",taskerOwner).andEqualTo("taskerseries",taskerSeries);

        if (result != null) {
            if ("true".equals(result)) {
                condition.getOredCriteria().get(0).andEqualTo("result",1);
            } else {
                condition.getOredCriteria().get(0).andEqualTo("result",-1);
            }
        }

        return tSystemlogMapper.selectCountByCondition(condition);

    }

    public void cleanLogs(String taskerOwner, String taskerName, String taskerSeries) {
        Condition condition = new Condition(TSystemlog.class);
        condition.createCriteria().andEqualTo("taskername",taskerName).andEqualTo("owner",taskerOwner).andEqualTo("taskerseries",taskerSeries);
        tSystemlogMapper.deleteByCondition(condition);

    }

}

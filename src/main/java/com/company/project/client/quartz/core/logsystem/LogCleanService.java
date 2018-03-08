package com.company.project.client.quartz.core.logsystem;


import com.company.project.model.TSystemlog;
import com.company.project.service.impl.TSystemlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

@Service
public class LogCleanService {

    @Autowired
    TSystemlogServiceImpl systemlogService;

    public void clean(String taskerName) {
            TSystemlog log = systemlogService.findBy("taskerName",taskerName);
            systemlogService.deleteById(log.getId());
    }

    public void clean(String taskerOwner, String taskerName, String taskerSeries) {
        Condition condition = new Condition(TSystemlog.class);
        condition.createCriteria().andEqualTo("taskerowner",taskerOwner)
                .andEqualTo("taskername",taskerName).andEqualTo("taskerseries",taskerSeries);
        List<TSystemlog> tSystemlogs = systemlogService.findByCondition(condition);
        systemlogService.deleteById(tSystemlogs.get(0).getId());
    }

}

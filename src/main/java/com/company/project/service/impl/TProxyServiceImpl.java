package com.company.project.service.impl;

import com.company.project.dao.TProxyMapper;
import com.company.project.model.TProxy;
import com.company.project.service.TProxyService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/08.
 */
@Service
@Transactional
public class TProxyServiceImpl extends AbstractService<TProxy> implements TProxyService {
    @Resource
    private TProxyMapper tProxyMapper;

}

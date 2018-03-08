package com.company.project.service.impl;

import com.company.project.dao.TSearchMapper;
import com.company.project.model.TSearch;
import com.company.project.service.TSearchService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/08.
 */
@Service
@Transactional
public class TSearchServiceImpl extends AbstractService<TSearch> implements TSearchService {
    @Resource
    private TSearchMapper tSearchMapper;

}

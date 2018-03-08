package com.company.project.service.impl;

import com.company.project.dao.TSearchCategoryMapper;
import com.company.project.model.TSearchCategory;
import com.company.project.service.TSearchCategoryService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/08.
 */
@Service
@Transactional
public class TSearchCategoryServiceImpl extends AbstractService<TSearchCategory> implements TSearchCategoryService {
    @Resource
    private TSearchCategoryMapper tSearchCategoryMapper;

}

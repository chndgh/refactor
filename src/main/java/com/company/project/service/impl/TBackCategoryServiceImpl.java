package com.company.project.service.impl;

import com.company.project.dao.TBackCategoryMapper;
import com.company.project.model.TBackCategory;
import com.company.project.service.TBackCategoryService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/08.
 */
@Service
@Transactional
public class TBackCategoryServiceImpl extends AbstractService<TBackCategory> implements TBackCategoryService {
    @Resource
    private TBackCategoryMapper tBackCategoryMapper;

}

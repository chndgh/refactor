package com.company.project.service.impl;

import com.company.project.dao.TFrontCategoryMapper;
import com.company.project.model.TFrontCategory;
import com.company.project.service.TFrontCategoryService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/08.
 */
@Service
@Transactional
public class TFrontCategoryServiceImpl extends AbstractService<TFrontCategory> implements TFrontCategoryService {
    @Resource
    private TFrontCategoryMapper tFrontCategoryMapper;

}

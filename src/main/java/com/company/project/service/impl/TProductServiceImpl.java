package com.company.project.service.impl;

import com.company.project.dao.TProductMapper;
import com.company.project.model.TProduct;
import com.company.project.service.TProductService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/08.
 */
@Service
@Transactional
public class TProductServiceImpl extends AbstractService<TProduct> implements TProductService {
    @Resource
    private TProductMapper tProductMapper;

}

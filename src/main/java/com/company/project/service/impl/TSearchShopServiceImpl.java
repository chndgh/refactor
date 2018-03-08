package com.company.project.service.impl;

import com.company.project.dao.TSearchShopMapper;
import com.company.project.model.TSearchShop;
import com.company.project.service.TSearchShopService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/08.
 */
@Service
@Transactional
public class TSearchShopServiceImpl extends AbstractService<TSearchShop> implements TSearchShopService {
    @Resource
    private TSearchShopMapper tSearchShopMapper;

}

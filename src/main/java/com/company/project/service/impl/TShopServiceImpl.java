package com.company.project.service.impl;

import com.company.project.dao.TShopMapper;
import com.company.project.model.TShop;
import com.company.project.service.TShopService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/03/08.
 */
@Service
@Transactional
public class TShopServiceImpl extends AbstractService<TShop> implements TShopService {
    @Resource
    private TShopMapper tShopMapper;

}

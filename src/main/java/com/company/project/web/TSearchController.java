package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TSearch;
import com.company.project.service.TSearchService;
import com.company.project.service.impl.search.TSearchServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/03/08.
*/
@RestController
@RequestMapping("/t/search")
public class TSearchController {


    @Autowired
    TSearchServiceImpl tSearchServiceImpl;


    @PostMapping("/generateByCategoryId/{id}/categoryId")
    public Result crawSearchResultByCategoryId(@PathVariable("id") String id) throws InterruptedException {
        tSearchServiceImpl.cateid = id;
        tSearchServiceImpl.init();
        tSearchServiceImpl.start();
        tSearchServiceImpl.join();
        return ResultGenerator.genSuccessResult();

    }
}

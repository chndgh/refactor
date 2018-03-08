package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TSearchCategory;
import com.company.project.service.TSearchCategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2018/03/08.
*/
@RestController
@RequestMapping("/t/search/category")
public class TSearchCategoryController {
    @Resource
    private TSearchCategoryService tSearchCategoryService;

    @PostMapping("/add")
    public Result add(TSearchCategory tSearchCategory) {
        tSearchCategoryService.save(tSearchCategory);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        tSearchCategoryService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TSearchCategory tSearchCategory) {
        tSearchCategoryService.update(tSearchCategory);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        TSearchCategory tSearchCategory = tSearchCategoryService.findById(id);
        return ResultGenerator.genSuccessResult(tSearchCategory);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TSearchCategory> list = tSearchCategoryService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

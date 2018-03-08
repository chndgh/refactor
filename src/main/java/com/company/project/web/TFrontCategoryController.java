package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TFrontCategory;
import com.company.project.service.TFrontCategoryService;
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
@RequestMapping("/t/front/category")
public class TFrontCategoryController {
    @Resource
    private TFrontCategoryService tFrontCategoryService;

    @PostMapping("/add")
    public Result add(TFrontCategory tFrontCategory) {
        tFrontCategoryService.save(tFrontCategory);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        tFrontCategoryService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TFrontCategory tFrontCategory) {
        tFrontCategoryService.update(tFrontCategory);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        TFrontCategory tFrontCategory = tFrontCategoryService.findById(id);
        return ResultGenerator.genSuccessResult(tFrontCategory);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TFrontCategory> list = tFrontCategoryService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TProxyVerify;
import com.company.project.service.TProxyVerifyService;
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
@RequestMapping("/t/proxy/verify")
public class TProxyVerifyController {
    @Resource
    private TProxyVerifyService tProxyVerifyService;

    @PostMapping("/add")
    public Result add(TProxyVerify tProxyVerify) {
        tProxyVerifyService.save(tProxyVerify);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        tProxyVerifyService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(TProxyVerify tProxyVerify) {
        tProxyVerifyService.update(tProxyVerify);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        TProxyVerify tProxyVerify = tProxyVerifyService.findById(id);
        return ResultGenerator.genSuccessResult(tProxyVerify);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<TProxyVerify> list = tProxyVerifyService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}

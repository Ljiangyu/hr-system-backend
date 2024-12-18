package com.lss.hrbackend.controller;

import com.lss.hrbackend.common.result.Result;
import com.lss.hrbackend.domain.Vo.DirectoryVo;
import com.lss.hrbackend.domain.entity.DirectoryTree;
import com.lss.hrbackend.service.DirectoryTreeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lss
 * @description
 * @createDate 2024/12/15-23:10
 * @tag
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Resource
    private DirectoryTreeService directoryTreeService;

    /**
     * 获取目录树
     * @return 结果
     */
    @GetMapping("/menu")
    public Result<DirectoryTree> getDirTree(){
       Result<DirectoryTree> result = directoryTreeService.getMenu();
        return result;
    }
}

package com.lss.hrbackend.controller;

import com.lss.hrbackend.common.result.Result;
import com.lss.hrbackend.domain.Vo.DirectoryVo;
import com.lss.hrbackend.domain.entity.DirectoryTree;
import com.lss.hrbackend.service.DirectoryTreeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @PostMapping("/add")
    public Result addDirTree(@RequestBody DirectoryTree directoryTree){
        Result result = directoryTreeService.addMenu(directoryTree);
        return result;
    }

    /**
     * 获取数据
     * @param id 部门编码
     * @return
     */
    @GetMapping ("/get/{id}")
    public Result getDirTree(@PathVariable("id") String id){
        Result result = directoryTreeService.getDetails(id);
        return result;
    }

    @PutMapping("/update/{id}")
    public Result updateDirTree(@PathVariable("id") String id,@RequestBody Map<String,String> map){
        System.out.println(map);
        return directoryTreeService.updateDirTree(id,map);
    }
}

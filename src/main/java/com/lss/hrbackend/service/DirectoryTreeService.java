package com.lss.hrbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lss.hrbackend.common.result.Result;
import com.lss.hrbackend.domain.Vo.DirectoryVo;
import com.lss.hrbackend.domain.entity.DirectoryTree;

import java.util.Map;


/**
* @author 江里的鱼
* @description 针对表【directory_tree】的数据库操作Service
* @createDate 2024-12-15 21:07:35
*/
public interface DirectoryTreeService extends IService<DirectoryTree> {

    Result<DirectoryTree> getMenu();

    /**
     * 添加目录
     * @return
     */
    Result addMenu(DirectoryTree directoryTree);

    /**
     * 获取部门
     * @param id
     * @return
     */
    Result getDetails(String id);

    Result updateDirTree(String id, Map<String, String> map);

    Result delDirTree(String id);
}

package com.lss.hrbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.lss.hrbackend.common.exception.QueryResException;
import com.lss.hrbackend.common.result.Result;
import com.lss.hrbackend.domain.entity.DirectoryTree;
import com.lss.hrbackend.domain.mapper.DirectoryTreeMapper;
import com.lss.hrbackend.service.DirectoryTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 江里的鱼
 * @description 针对表【directory_tree】的数据库操作Service实现
 * @createDate 2024-12-15 21:07:35
 */
@Service
@Slf4j
public class DirectoryTreeServiceImpl extends ServiceImpl<DirectoryTreeMapper, DirectoryTree>
        implements DirectoryTreeService {
    @Override
    public Result addMenu(DirectoryTree directoryTree) {
        int insert = this.baseMapper.insert(directoryTree);
        if (insert > 0)
            return Result.success(null);
        else return Result.error("更新失败！");
    }

    /**
     * 获取目录树
     *
     * @return
     */
    @Override
    public Result<DirectoryTree> getMenu() {
        List<DirectoryTree> list = Db.list(DirectoryTree.class);
        if (list == null || list.size() == 0) {
            throw new QueryResException("获取不到数据请重试");
        }
        DirectoryTree root = null;
        for (DirectoryTree directory : list) {
            if (directory.getParentId() == null) {
                root = directory;
                break;
            }
        }
        buildMenu(list, root);
        log.info("root ====> {}", root);
        return Result.success(root);
    }

    /**
     * 构造目录树
     *
     * @param list 目录列表
     * @param root root
     */
    private void buildMenu(List<DirectoryTree> list, DirectoryTree root) {
        List<DirectoryTree> children = new ArrayList<>();
        for (DirectoryTree directory : list) {
            if (directory.getParentId() != null && directory.getParentId().equals(root.getId())) {
                // 检查是否已经添加了节点，避免重复添加
                if (!children.contains(directory)) {
                    children.add(directory);
                    // 递归构建子节点
                    buildMenu(list, directory);
                }
            }
        }
        // 将构建好的子节点列表赋值给当前节点的children属性
        root.setChildren(children);
    }

}





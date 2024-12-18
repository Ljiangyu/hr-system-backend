package com.lss.hrbackend.domain.Vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.lss.hrbackend.domain.entity.DirectoryTree;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lss
 * @description
 * @createDate 2024/12/15-23:12
 * @tag
 */
@Data
public class DirectoryVo implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private Integer parentId;

    private List<DirectoryTree> children;
    private static final long serialVersionUID = 1L;
}

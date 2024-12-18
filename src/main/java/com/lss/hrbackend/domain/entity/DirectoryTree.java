package com.lss.hrbackend.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @TableName directory_tree
 */
@TableName(value ="directory_tree")
@Data
public class DirectoryTree implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private Integer parentId;
    @TableField(exist = false)
    private List<DirectoryTree> children;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

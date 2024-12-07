package com.lss.hrbackend.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户描述
     */
    private String description;

    /**
     * 用户创建时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date createTime;

    /**
     * 用户修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)

    private Date updateTime;

    /**
     * 用户删除标识，0：正常，1：已删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

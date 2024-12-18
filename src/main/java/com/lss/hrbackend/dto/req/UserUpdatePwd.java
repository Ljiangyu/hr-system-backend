package com.lss.hrbackend.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author lss
 * @description 修改密码请求体
 * @createDate 2024/12/13-11:29
 * @tag
 */
@Data
public class UserUpdatePwd {
    /**
     * 用户名
     */
    @Schema(description = "用户名",example = "admin111")
    private String username;

    /**
     * 用户密码
     */
    @Schema(description = "用户密码",example = "123456")
    private String oldPassword;

    /**
     * 用户新密码
     */
    @Schema(description = "用户密码",example = "123456")
    private String newPassword;
}

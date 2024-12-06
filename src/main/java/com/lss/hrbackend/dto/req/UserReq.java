package com.lss.hrbackend.dto.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author lss
 * @description 用户请求数据
 * @createDate 2024/12/6-14:21
 */
@Data
public class UserReq {
    /**
     * 用户名
     */
    @Schema(description = "用户名",example = "admin111")
    private String username;

    /**
     * 用户密码
     */
    @Schema(description = "用户密码",example = "123456")
    private String password;

    /**
     * 用户描述
     */
    @Schema(description = "用户描述",example = "this is a good man")
    private String description;
}

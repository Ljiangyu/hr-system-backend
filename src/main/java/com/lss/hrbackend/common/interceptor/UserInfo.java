package com.lss.hrbackend.common.interceptor;

import lombok.Data;

/**
 * @author lss
 * @description user信息类
 * @createDate 2024/12/6-12:47
 */
@Data
public class UserInfo {
    private String username;
    private String password;
    private String token;
}

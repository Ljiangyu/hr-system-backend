package com.lss.hrbackend.common.userConfig;

import lombok.Builder;
import lombok.Data;

/**
 * @author lss
 * @description user信息类
 * @createDate 2024/12/6-12:47
 */
@Data
@Builder
public class UserInfo {
    private String username;
    private String password;
    private String token;
}

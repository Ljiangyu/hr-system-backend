package com.lss.hrbackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lss.hrbackend.common.result.Result;
import com.lss.hrbackend.common.userConfig.UserInfo;
import com.lss.hrbackend.domain.entity.User;
import com.lss.hrbackend.dto.req.UserReq;
import com.lss.hrbackend.dto.req.UserUpdatePwd;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author lss
 * @description 针对表【t_user】的数据库操作Service
 * @createDate 2024-12-06 11:38:50
 */
public interface UserService extends IService<User> {


    /**
     * 注册用户
     * @param userReq
     */
    void register(UserReq userReq);

    Result<String> login(UserInfo userInfo, HttpServletResponse httpServletResponse);

    User getUserInfo();

    Result updatePwd(UserUpdatePwd userReq);


}

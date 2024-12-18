package com.lss.hrbackend.controller;

import com.lss.hrbackend.common.result.Result;
import com.lss.hrbackend.common.userConfig.UserInfo;
import com.lss.hrbackend.domain.entity.User;
import com.lss.hrbackend.dto.req.UserReq;
import com.lss.hrbackend.dto.req.UserUpdatePwd;
import com.lss.hrbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author lss
 * @description
 * @createDate 2024/12/6-14:19
 */
@RestController
@RequestMapping("/api")
@Tag(name = "user")
@Slf4j(topic = "user")
public class UserController {

    /**
     * todo 用户名不可重复
     */
    @Resource
    private UserService userService;


    @PostMapping("/user/updatePwd")
    @Operation(summary = "修改密码",description = "修改用户密码")
    public Result updatePassword(@RequestBody UserUpdatePwd userReq){
        return userService.updatePwd(userReq);
    }

    @PostMapping("/user/register")
    @Operation(summary = "用户注册", description = "用户注册desc")
    public void register(UserReq userReq) {
        userService.register(userReq);
    }

    /**
     * todo 需要加一个判断用户是否已存在
     * 用户登陆
     *
     * @return
     */
    @PostMapping("/user/login")
    @Operation(summary = "用户登陆", description = "用户登陆")
    public Result<String> login(@RequestBody UserInfo userInfo, HttpServletResponse httpServletResponse) {
        Result<String> result = userService.login(userInfo, httpServletResponse);
        return result;
    }

    /**
     * 获取用户数据
     *
     * @return
     */
    @GetMapping("/user/getInfo")
    @Operation(summary = "获取用户信息", description = "获取用户信息")
    public Result<User> getUserInfo() {
        User user = userService.getUserInfo();
        log.info("result ===> {}",Result.success(user));
        return Result.success(user);
    }
}

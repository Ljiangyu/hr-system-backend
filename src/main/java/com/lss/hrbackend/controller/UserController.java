package com.lss.hrbackend.controller;

import com.lss.hrbackend.dto.req.UserReq;
import com.lss.hrbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lss
 * @description
 * @createDate 2024/12/6-14:19
 */
@RestController
@RequestMapping("/api")
@Tag(name = "user")
public class UserController {
    /**
     * todo 用户名不可重复
     */
    @Resource
    private UserService userService;
    @PostMapping("/user/register")
    @Operation(summary = "用户注册",description = "用户注册desc")
    public void register(UserReq userReq){
        userService.register(userReq);
    }
}

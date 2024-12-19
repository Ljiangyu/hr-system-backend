package com.lss.hrbackend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lss.hrbackend.common.constant.HrConstant;
import com.lss.hrbackend.common.exception.BusinessException;
import com.lss.hrbackend.common.result.Result;
import com.lss.hrbackend.common.userConfig.UserContext;
import com.lss.hrbackend.common.userConfig.UserInfo;
import com.lss.hrbackend.domain.entity.User;
import com.lss.hrbackend.domain.mapper.UserMapper;
import com.lss.hrbackend.dto.req.UserReq;
import com.lss.hrbackend.dto.req.UserUpdatePwd;
import com.lss.hrbackend.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author lss
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2024-12-06 11:38:50
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final RedisTemplate<String, String> redisTemplate;
    private final UserMapper userMapper;

    // 构造函数注入
    public UserServiceImpl(RedisTemplate<String, String> redisTemplate, UserMapper userMapper) {
        this.redisTemplate = redisTemplate;
        this.userMapper = userMapper;
    }

    @Override
    public User getUserInfo() {
        String token = UserContext.getCurrentUser().getToken();
        String userJson = redisTemplate.opsForValue().get(HrConstant.RedisKey.LOGIN_KEY + token);
        User user = JSON.parseObject(userJson, User.class);
        log.info("user ====> {}", user);
        return user;
    }


    @Override
    public Result<String> login(UserInfo userInfo, HttpServletResponse httpServletResponse) {
        // TODO 登录逻辑
//        httpServletResponse.setHeader("token", "123456");
        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery(User.class)
                .ge(User::getUsername, user.getUsername())
                .eq(User::getPassword, user.getPassword());
        User selectedOne = userMapper.selectOne(userLambdaQueryWrapper);
        if (selectedOne == null) {
            log.info("....................................");
            throw new RuntimeException("用户不存在");
        }

        String uuid = IdUtil.fastUUID().replace("-", "");
        // 将查询到的用户存入 Redis 中，使用用户的 ID 或 username 作为 Redis key
        String redisKey = HrConstant.RedisKey.LOGIN_KEY + uuid;  // 使用用户的 ID 作为 Redis key，您可以根据需求修改
        // 将 selectedOne 转换为 JSON 字符串
        String userJson = JSON.toJSONString(selectedOne);  // 使用 FastJson 或其他 JSON 库进行序列化
        httpServletResponse.setHeader(HrConstant.Web.REQUEST_HEAD, uuid);
        // 将数据存入 Redis，设置有效期（例如 1 小时）
        redisTemplate.opsForValue().set(redisKey, userJson, 1, TimeUnit.HOURS);

        // 返回用户信息，或者根据需求返回其他信息
        log.info("uuid ====> {}", uuid);
        return Result.success(uuid);
    }

    /**
     * 注册用户
     *
     * @param userReq
     */
    @Override
    public void register(UserReq userReq) {
        baseMapper.insert(BeanUtil.toBean(userReq, User.class));
//        User user = new User();
//        BeanUtils.copyProperties(userReq,user);
//        save(user);
    }

    @Override
    public Result updatePwd(UserUpdatePwd userReq) {
        log.info("begin updatePwd");
        UserInfo currentUser = UserContext.getCurrentUser();

        if (currentUser.getPassword().equals(userReq.getOldPassword())) {
            int update = this.getBaseMapper().update(Wrappers
                    .<User>lambdaUpdate()
                    .eq(User::getPassword, userReq.getOldPassword())
                    .eq(User::getUsername,currentUser.getUsername())
                    .set(User::getPassword,userReq.getNewPassword()));
            if (update > 0) {
                Boolean delete = redisTemplate.delete(HrConstant.Web.REQUEST_HEAD + currentUser.getToken());
                return Result.success(null);
            }else {
                return Result.failure(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
            }
        }else{
            throw new BusinessException("修改用户密码异常，请重新登陆");
        }

    }

}





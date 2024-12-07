package com.lss.hrbackend.common.constant;

/**
 * @author lss
 * @description key常量类
 * @createDate 2024/12/7-19:15
 */
public class HrConstant {
    /**
     * redis相关
     */
    public final static class RedisKey {
        //用户登陆 redis key
        public final static String LOGIN_KEY = "user:login:";
    }

    /**
     * web相关常量
     */
    public final static class Web{
        public final static String REQUEST_HEAD = "hr-token";
    }

}

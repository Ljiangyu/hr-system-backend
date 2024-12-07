package com.lss.hrbackend.common.userConfig;

/**
 * @author lss
 * @description 用户上下文
 * @createDate 2024/12/6-11:50
 */
public class UserContext {
    private static final ThreadLocal<UserInfo> userThreadLocal = new ThreadLocal<>();

    public static UserInfo getCurrentUser() {
        return userThreadLocal.get();
    }
    public static void setCurrentUser(UserInfo user) {
        userThreadLocal.set(user);
    }

    /**
     * 避免内存泄露
     */
    public static void remove() {
        userThreadLocal.remove();
    }
}

package com.lss.hrbackend.common.UserContext;

import com.lss.hrbackend.domain.entity.User;

/**
 * @author lss
 * @description 用户上下文
 * @createDate 2024/12/6-11:50
 */
public class UserContext {
    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static User getCurrentUser() {
        return userThreadLocal.get();
    }
    public static void setCurrentUser(User user) {
        userThreadLocal.set(user);
    }

    /**
     * 避免内存泄露
     */
    public static void remove() {
        userThreadLocal.remove();
    }
}

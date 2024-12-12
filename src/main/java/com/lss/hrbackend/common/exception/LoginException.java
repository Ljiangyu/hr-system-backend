package com.lss.hrbackend.common.exception;

/**
 * @author lss
 * @description
 * @createDate 2024/12/12-23:42
 */
public class LoginException extends  RuntimeException{
    public LoginException(String message) {
        super(message);
    }
}

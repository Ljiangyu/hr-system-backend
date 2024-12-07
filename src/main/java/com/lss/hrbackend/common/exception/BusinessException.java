package com.lss.hrbackend.common.exception;
import java.lang.RuntimeException;

/**
 *
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}

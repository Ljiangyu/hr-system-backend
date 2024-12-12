package com.lss.hrbackend.config;

import com.lss.hrbackend.common.exception.BusinessException;
import com.lss.hrbackend.common.exception.LoginException;
import com.lss.hrbackend.common.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;



import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有Exception类型的异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<String>> handleException(Exception ex, WebRequest request) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "系统错误: " + ex.getMessage());
    }
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Result<String>> handleLoginException(Exception ex, WebRequest request) {
        return buildResponse(HttpStatus.UNAUTHORIZED, "系统错误: " + ex.getMessage());
    }
    /**
     * 处理业务异常 (例如自定义异常)
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Result<String>> handleBusinessException(BusinessException ex, WebRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /**
     * 处理方法参数校验异常 (如 @Valid 校验失败)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Result<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        // 获取校验失败的消息
        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        String errorMessage = String.join(", ", errorMessages);
        return buildResponse(HttpStatus.BAD_REQUEST, "参数校验失败: " + errorMessage);
    }

    /**
     * 处理请求参数绑定异常（例如 BindException）
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Result<String>> handleBindException(BindException ex, WebRequest request) {
        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        String errorMessage = String.join(", ", errorMessages);
        return buildResponse(HttpStatus.BAD_REQUEST, "参数绑定失败: " + errorMessage);
    }

    /**
     * 构建统一的错误响应格式
     */
    private ResponseEntity<Result<String>> buildResponse(HttpStatus status, String message) {
        Result<String> result = new Result<>();
        result.setCode(status.value());
        result.setMessage(message);
        return new ResponseEntity<>(result, status);
    }
}

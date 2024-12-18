package com.lss.hrbackend.common.result;

import com.lss.hrbackend.common.exception.ResponseCode;

/**
 * @author lss
 * @description 返回结果类
 * @createDate 2024/12/7-19:20
 */
public class Result<T> {

    private int code;        // 状态码
    private String message;  // 提示消息
    private T data;          // 数据部分

    // 无参构造方法
    public Result() {}

    // 带参构造方法
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(int code) {
        this.code = code;
    }

    // 成功响应的静态方法
    public static <T> Result<T> success(T data) {
        return new Result<>(ResponseCode.SUCCESS_CODE, "操作成功", data);
    }

    // 失败响应的静态方法
    public static <T> Result<T> failure(int code) {
        return new Result<>(code);
    }

    // 错误响应的静态方法
    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null);
    }

    // 状态码和消息的 getter 和 setter 方法

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}


package com.chen.demo.exception;

import com.chen.demo.enums.ReturnCodeEnum;
import lombok.Data;

/**
 * 业务异常
 */
@Data
public class BusinessException extends RuntimeException {
    private Integer code;

    private String msg;

    public BusinessException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(ReturnCodeEnum e) {
        this.code = e.getCode();
        this.msg = e.getMessage();
    }

    public BusinessException(String message, Integer code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String message, Throwable cause, Integer code, String msg) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(Throwable cause, Integer code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
    }
}

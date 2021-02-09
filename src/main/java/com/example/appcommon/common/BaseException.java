package com.example.appcommon.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: wcg
 * @Date: 2021/2/9 17:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
    private RestCode restCode;
    private Object[] args;
    private String message;
    private Throwable cause;
    
    public BaseException(RestCode restCode){
        this.restCode = restCode;
    }
    
    public BaseException(RestCode restCode, Object[] args, String message, Throwable cause1) {
        this.restCode = restCode;
        this.args = args;
        this.message = message;
        this.cause = cause1;
    }
}

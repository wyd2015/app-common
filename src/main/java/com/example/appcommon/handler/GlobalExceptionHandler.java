package com.example.appcommon.handler;

import com.example.appcommon.common.BaseException;
import com.example.appcommon.common.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wcg
 * @Date: 2021/2/10 9:24
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BaseException.class)
    public RestResult defaultExceptionHandler(HttpServletRequest request, BaseException e) {
        log.error("接口[{}]发生异常: ", request.getRequestURI(), e);
        return new RestResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), request.getRequestURI());
    }
    
}

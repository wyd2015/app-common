package com.example.appcommon.interceptor;

import com.example.appcommon.annotation.RestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author: wcg
 * @Date: 2021/2/9 13:49
 */
@Slf4j
@Component
public class RestResultInterceptor implements HandlerInterceptor {
    private static final String REST_RESULT_ANNOTATION = "REST_RESULT_ANNOTATION";
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> aClass = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();
            if(aClass.isAnnotationPresent(RestWrapper.class)) {
                log.info("在类[{}]上发现了 @RestWrapper 注解", aClass.getName());
                request.setAttribute(REST_RESULT_ANNOTATION, aClass.getAnnotation(RestWrapper.class));
            } else if (method.isAnnotationPresent(RestWrapper.class)) {
                log.info("在方法[{}]上发现了 @RestWrapper 注解", method.getName());
                request.setAttribute(REST_RESULT_ANNOTATION, method.getAnnotation(RestWrapper.class));
            }
        }
        return true;
    }
}
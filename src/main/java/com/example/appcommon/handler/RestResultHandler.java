package com.example.appcommon.handler;

import com.example.appcommon.annotation.RestWrapper;
import com.example.appcommon.common.BaseException;
import com.example.appcommon.common.RestResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wcg
 * @Date: 2021/2/9 13:55
 */
@Slf4j
@ControllerAdvice(annotations = RestWrapper.class)
public class RestResultHandler implements ResponseBodyAdvice<Object> {
    
    private static final String REST_RESULT_ANNOTATION = "REST_RESULT_ANNOTATION";
    
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        
        //判断请求是否有标记
        RestWrapper restWrapper = (RestWrapper) request.getAttribute(REST_RESULT_ANNOTATION);
        return restWrapper == null ? false : true;
    }
    
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        //自定义异常处理
        if(body instanceof BaseException) {
            BaseException baseException = (BaseException) body;
            return new RestResult(baseException.getRestCode().code(), baseException.getMessage(), request.getURI().getPath());
        }
    
        RestResult restResult = RestResult.success(body, request.getURI().getPath());
        if(returnType.getGenericParameterType().equals(String.class)){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(restResult);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("将 RestReponse 对象序列化为 json 字符串时异常：", e);
            }
        }
        return restResult;
    }
}

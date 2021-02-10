package com.example.appcommon.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * @Author: wcg
 * @Date: 2021/2/9 14:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ErrorResult extends RestResult{
    
    private String path;
    
    public ErrorResult(RestCode restCode){
        super(restCode);
    }
    
    public ErrorResult(RestCode restCode, WebRequest request){
        super(restCode, extractRequestURI(request));
    }
    
    public ErrorResult (RestCode restCode, Exception e){
        super(restCode, e.getMessage());
    }
    
    public ErrorResult (RestCode restCode, Exception e, WebRequest request){
        super(restCode, extractRequestURI(request) + ": " +e.getMessage());
    }
    
    private static String extractRequestURI(WebRequest request) {
        if(request instanceof ServletWebRequest) {
            ServletWebRequest webRequest = (ServletWebRequest) request;
            return webRequest.getRequest().getRequestURI();
        }
        
        return request.getDescription(false);
    }
    
}

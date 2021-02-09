package com.example.appcommon.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: wcg
 * @Date: 2021/2/9 14:05
 */
@Data
@NoArgsConstructor
public class RestResult implements Serializable {
    private Integer code;
    private String msg;
    private Object data;
    private Date timestamp = new Date();
    
    public RestResult(RestCode restCode) {
        this.code = restCode.code();
        this.msg = restCode.msg();
    }
    
    public RestResult(RestCode restCode, Object data) {
        this.code = restCode.code();
        this.msg = restCode.msg();
        this.data = data;
    }
    
    public static RestResult success(){
        return new RestResult(RestCode.SUCCESS);
    }
    
    public static RestResult success(Object data){
        return new RestResult(RestCode.SUCCESS, data);
    }
    
    public static RestResult failure(RestCode restCode) {
        return new RestResult(restCode);
    }
    
    public static RestResult failure(Object data){
        return new RestResult(RestCode.SUCCESS, data);
    }
    
}

package com.example.appcommon.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import static com.example.appcommon.common.RestCode.SUCCESS;

/**
 * @Author: wcg
 * @Date: 2021/2/9 14:05
 */
@Data
@NoArgsConstructor
public class RestResult implements Serializable {
    private Integer code;
    private Object data;
    private String msg;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.sss")
    private Date timestamp = new Date();
    private String path;
    
    public RestResult(RestCode restCode, String path) {
        this.code = restCode.code();
        this.msg = restCode.msg();
        this.path = path;
    }
    
    public RestResult(RestCode restCode, String path, Object data) {
        this.code = restCode.code();
        this.msg = restCode.msg();
        this.path = path;
        this.data = data;
    }
    
    public RestResult(Integer code, String msg, String path) {
        this.code = code;
        this.path = path;
        this.msg = msg;
    }
    
    public static RestResult success(String path){
        return new RestResult(SUCCESS, path);
    }
    
    public static RestResult success(Object data, String path){
        return new RestResult(SUCCESS, path, data);
    }
    
    public static RestResult failure(RestCode restCode, String path) {
        return new RestResult(restCode, path);
    }
    
    public static RestResult failure(Object data, String path){
        return new RestResult(SUCCESS, path, data);
    }
}

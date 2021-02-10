package com.example.appcommon.common;

/**
 * @Author: wcg
 * @Date: 2021/2/9 14:06
 */
public enum RestCode {
    SUCCESS(200, "成功"),
    FAILED(-1, "失败"),
    NO_AUTH(401,"未登录"),
    NO_PERMISSION(402,"你没得权限"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器异常请联系管理员"),
    
    PARAM_INVALID(1000, "参数无效"),
    PARAM_BLANK(1000, "参数为空"),
    PARAM_TYPE_BIND_ERROR(1000, "参数类型错误"),
    PARAM_NOT_COMPLETE(1000, "参数缺失"),
    
    USER_NOT_LOGIN(100, "用户未登录"),
    USER_LOGIN_ERROR(100, "用户名或密码错误"),
    USER_ACCOUNT_FORBIDDEN(100, "账号已禁用"),
    USER_NOT_EXIST(100, "用户不存在"),
    USER_HAS_EXIST(100, "用户已存在"),
    ;
    private Integer code;
    private String msg;
    
    RestCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public Integer code(){
        return this.code;
    }
    
    public String msg(){
        return this.msg;
    }
}

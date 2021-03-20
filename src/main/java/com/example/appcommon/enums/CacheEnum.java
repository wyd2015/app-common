package com.example.appcommon.enums;

import lombok.AllArgsConstructor;

/**
 * 缓存定义枚举枚举类
 *
 * @Author: wcg
 * @Date: 2021/3/2 22:32
 **/
@AllArgsConstructor
public enum CacheEnum {
    /**
     * JWT缓存键
     */
    CACHE_JWT_TOKEN("userToken", 7 * 24 * 3600),
    
    /**
     * 短信验证码缓存键
     */
    CACHE_SMS_CODE("smsCode", 5 * 60),
    ;
    
    /**
     * 缓存键名称
     */
    private String cacheName;
    
    /**
     * 缓存过期时间，单位：秒
     */
    private int ttlSecond;
    
    public String cacheName(){
        return this.cacheName;
    }
    
    public int ttlSecond(){
        return this.ttlSecond;
    }
    
}

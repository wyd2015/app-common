package com.example.appcommon.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;

/**
 * @Author: wcg
 * @Date: 2021/2/3 15:23
 */
@Slf4j
@Service
public class TestService {
    
    public String isLoginSuccess() {
        String ackJson = null;
        int count = 0;
        try {
            while(StrUtil.isBlank(ackJson)){
                TimeUnit.SECONDS.sleep(1);
                count ++;
                if(StrUtil.isNotBlank(ackJson)){
                    log.info("Never executed ...");
                }
                if(count >= 60){
                    log.info("========================等待超过60秒，未获取到登录结果！");
                    return "等待超过60秒，未获取到登录结果！";
                }
            }
            
            return "timeout";
        } catch (InterruptedException e) {
            log.error("重复[{}]次获取redis中的登录结果时线程中断异常：", count, e);
        }
        
        return "failed";
    }
}

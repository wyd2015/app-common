package com.example.appcommon;

import com.thebeastshop.forest.springboot.annotation.ForestScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wcg
 */
@SpringBootApplication
@ForestScan(basePackages = "com.example.appcommon.api")
public class AppCommonApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AppCommonApplication.class, args);
    }
    
}

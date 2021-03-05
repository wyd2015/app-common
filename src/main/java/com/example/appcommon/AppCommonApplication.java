package com.example.appcommon;

import com.thebeastshop.forest.springboot.annotation.ForestScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wcg
 */
@EnableOpenApi
@SpringBootApplication
@ForestScan(basePackages = "com.example.appcommon.api")
public class AppCommonApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AppCommonApplication.class, args);
    }
    
}

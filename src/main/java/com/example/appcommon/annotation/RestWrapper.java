package com.example.appcommon.annotation;

import java.lang.annotation.*;

/**
 * @Author: wcg
 * @Date: 2021/2/9 13:48
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RestWrapper {
}

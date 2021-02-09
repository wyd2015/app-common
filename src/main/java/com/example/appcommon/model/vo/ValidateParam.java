package com.example.appcommon.model.vo;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author: wcg
 * @Date: 2021/2/9 15:15
 */
@Data
public class ValidateParam {
    
    @NotNull(message = "用户ID不能为空！")
    private int id;
    
    @NotNull(message = "用户名不能为空！")
    @Size(min = 6, max = 11, message = "用户名长度必须是 6~11 位的字符！")
    private String userName;
    
    @NotNull(message = "用户密码不能为空！")
    @Size(min = 6, max = 11, message = "用户密码长度必须是 6~11 位的字符！")
    private String password;
    
    @NotNull(message = "用户邮箱不能为空！")
    @Email(message = "用户邮箱格式不正确！")
    private String email;
    
}

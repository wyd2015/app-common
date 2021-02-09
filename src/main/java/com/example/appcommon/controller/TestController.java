package com.example.appcommon.controller;

import com.example.appcommon.annotation.RestWrapper;
import com.example.appcommon.common.BaseException;
import com.example.appcommon.common.RestCode;
import com.example.appcommon.model.po.SysUser;
import com.example.appcommon.model.vo.FileVO;
import com.example.appcommon.model.vo.ValidateParam;
import com.example.appcommon.service.ExcelService;
import com.example.appcommon.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: wcg
 * @Date: 2021/2/3 15:26
 */
@Slf4j
@RestWrapper
@RestController
@RequestMapping("/api")
@Api(tags = "api-test")
public class TestController {
    
    @Autowired
    private TestService testService;
    @Autowired
    private ExcelService excelService;
    
    @GetMapping("/timeout")
    @ApiOperation("验证超时")
    public String validate() {
        return testService.isLoginSuccess();
    }
    
    
    @ApiOperation("解析Excel文档")
    @PostMapping(value = "/parseExcel", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public List<SysUser> parseExcel(@RequestBody FileVO fileVO) {
        return excelService.parseExcel(fileVO.getFile());
    }
    
    @GetMapping("/log")
    @ApiOperation("param validate")
    public Object showLog(@Valid ValidateParam param){
        return param;
    }
    
    @GetMapping("/error")
    @ApiOperation("测试异常时的返回信息")
    public Object getException(){
        throw new BaseException(RestCode.FAILED);
    }
    
}

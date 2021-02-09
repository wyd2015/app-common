package com.example.appcommon.service;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import com.example.appcommon.model.po.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wcg
 * @Date: 2021/2/5 13:52
 */
@Slf4j
@Service
public class ExcelService {

    public List<SysUser> parseExcel(MultipartFile excel) {
        List<SysUser> userList = new ArrayList<>();
        try {
            ExcelUtil.readBySax(excel.getInputStream(), 0, excelRowHandler(userList));
        } catch (IOException e) {
            log.error("解析Excel异常：", e);
        }
        return userList;
    }
    
    private RowHandler excelRowHandler(List<SysUser> userList){
        return new RowHandler() {
            @Override
            public void handle(int sheetIndex, long rowIndex, List<Object> rowList) {
                if(!ObjectUtils.isEmpty(rowList)){
                    if(rowIndex > 0){
                        SysUser sysUser = new SysUser();
                        sysUser.setId((int) (rowIndex));
                        sysUser.setOrgName(rowList.get(0).toString());
                        sysUser.setGroupName(rowList.get(1).toString());
                        sysUser.setUserName(rowList.get(2).toString());
                        sysUser.setLoginName(rowList.get(3).toString());
                        sysUser.setPassword(rowList.get(4).toString());
                        sysUser.setGender(rowList.get(5).toString());
                        sysUser.setPoliticalStatus(rowList.get(6).toString());
                        sysUser.setPosition(rowList.get(7).toString());
                        sysUser.setRecUnit(rowList.get(8).toString());
                        sysUser.setEmail(rowList.get(9).toString());
                        sysUser.setPhone(rowList.get(10).toString());
                        sysUser.setQQ(rowList.get(11).toString());
                        userList.add(sysUser);
                    }
                }
            }
        };
    }
    
}

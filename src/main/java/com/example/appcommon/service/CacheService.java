package com.example.appcommon.service;

import com.example.appcommon.dao.PeopleRepository;
import com.example.appcommon.model.po.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 业务缓存类
 *
 * @Author: wcg
 * @Date: 2021/3/1 22:56
 **/
@Service
public class CacheService {
    
    @Autowired
    private PeopleRepository peopleRepository;
    
    public List<Integer> getList(){
        return Arrays.asList(1,2,3,4,5);
    }
    
    public List<People> getPeopleList(int age){
        List<People> peopleList = peopleRepository.findPeopleByAge(age);
        return peopleList;
    }
    
}

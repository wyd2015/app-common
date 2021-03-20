package com.example.appcommon.model.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * People实体类
 *
 * @Author: wcg
 * @Date: 2021/3/1 23:10
 **/
@Entity
public class People implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String name;
    
    private int age;
}

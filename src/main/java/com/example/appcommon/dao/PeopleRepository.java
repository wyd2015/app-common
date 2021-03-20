package com.example.appcommon.dao;

import com.example.appcommon.model.po.People;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * PeopleDAO接口
 *
 * @Author: wcg
 * @Date: 2021/3/1 23:14
 **/
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PeopleRepository extends PagingAndSortingRepository<People, Long> {
    
    List<People> findByName(@Param("name") String name);
    
    List<People> findPeopleByAge(int age);
    
}

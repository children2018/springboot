package com.spring.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.spring.springboot.model.User;

/**
 * Created by zl on 2015/8/27.
 */
public interface UserMapper {
	
    public List<User> findUserInfo();
    
    public void insert(User user);

    public void insertList(@Param("list") List<User> userList);
    
}

package com.spring.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springboot.mapper.UserMapper;
import com.spring.springboot.model.User;

/**
 * Created by zl on 2015/8/27.
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getUserInfo(){
        List<User> user=userMapper.findUserInfo();
        //User user=null;
        return user;
    }
    
    public void insertinfo() {
    	double s = Math.random();
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setAge((int)(s% 100000));
		user.setName("jack" + s);
		user.setPassword("password" + s);
		userMapper.insert(user);
    }
    
    public void inserts() {
    	for (int i=1; i<= 100000;i++) {
    		User user = new User();
    		user.setId(UUID.randomUUID().toString());
    		user.setAge(i);
    		user.setName("jack" + i);
    		user.setPassword("password" + i);
    		userMapper.insert(user);
    	}
    }
    
    public void insertsList() {
    	List<User> userList = new ArrayList<User>();
    	for (int i=1; i<= 1000000;i++) {
    		User user = new User();
    		user.setId(UUID.randomUUID().toString());
    		user.setAge(i);
    		user.setName("jack" + i);
    		user.setPassword("password" + i);
    		userList.add(user);
    		if (userList.size() % 5000 == 0) {
    			userMapper.insertList(userList);
    			userList.clear();
    		}
    	}
    	
    	if (userList.size() > 0) {
			userMapper.insertList(userList);
			userList.clear();
		}
    	
    }
    
    public void process() {
    	while(true) {
    	}
    }
    
    public void insertInfoWithFork() {
    	List<User> userList = new ArrayList<User>();
    	for (int i=1; i<= 100000;i++) {
    		User user = new User();
    		user.setId(UUID.randomUUID().toString());
    		user.setAge(i);
    		user.setName("jack" + i);
    		user.setPassword("password" + i);
    		userList.add(user);
    	}
    	
    	HandlerTask ht = new HandlerTask(userMapper, userList, 0, userList.size(), 1);
    	ForkJoinPool fork = new ForkJoinPool(Runtime.getRuntime().availableProcessors() * 2);
    	fork.invoke(ht);
    	//fork.shutdown();
    }
    
    public void insertsListWithFork() {
    	List<User> userList = new ArrayList<User>();
    	for (int i=1; i<= 1000000;i++) {
    		User user = new User();
    		user.setId(UUID.randomUUID().toString());
    		user.setAge(i);
    		user.setName("jack" + i);
    		user.setPassword("password" + i);
    		userList.add(user);
    	}
    	
    	HandlerTask ht = new HandlerTask(userMapper, userList, 0, userList.size(), 3000);
    	ForkJoinPool fork = new ForkJoinPool(Runtime.getRuntime().availableProcessors() * 2);
    	fork.invoke(ht);
    	//fork.shutdown();
    }

}

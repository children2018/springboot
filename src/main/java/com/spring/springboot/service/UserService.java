package com.spring.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.spring.springboot.mapper.UserMapper;
import com.spring.springboot.model.User;

/**
 * Created by zl on 2015/8/27.
 */

@Service
public class UserService {
	
	private Logger logger = Logger.getLogger(UserService.class);

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
		logger.info("insert.user:" + JSON.toJSONString(user));
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
    	int s = new Random().nextInt() % 4;
    	for (int i=1; i<= 100000000;i++) {
    		User user = new User();
    		user.setId(UUID.randomUUID().toString());
    		user.setAge(i);
    		user.setName("jack" + i);
    		user.setPassword("password" + i);
    		user.setArea("1");
    		userList.add(user);
    	}
    	
    	HandlerTask ht = new HandlerTask(userMapper, userList, 0, userList.size(), 3000);
    	ForkJoinPool fork = new ForkJoinPool(Runtime.getRuntime().availableProcessors() * 2);
    	fork.invoke(ht);
    	//fork.shutdown();
    }
    
    /**
     * 为分区作准备创建数据
     * 花费时间：cost:6894791
     * 
     * select count(1) from user;
	 * 到 127.0.0.1 的连接于 2019-12-14 13:50:09 关闭 
     * Affected rows: 0  已找到记录: 1  警告: 0  持续时间 1 query: 00:23:47 
     */
    public void insertsListWithForkWithSubregion() {
    	Random r = new Random();
    	List<User> userList = new ArrayList<User>();
    	for (int i=1; i<= 100000000;i++) {
    		User user = new User();
    		user.setId("" + i + UUID.randomUUID().toString());
    		user.setAge(i);
    		user.setName("jack" + i);
    		user.setPassword("password" + i);
    		user.setRevision("1");
    		user.setArea("" + Math.abs(r.nextInt() % 4));
    		userList.add(user);
    		if (userList.size() % 30000 == 0) {
    			List<User> saveList = new ArrayList<User>();
    			saveList.addAll(userList);
    			HandlerTask ht = new HandlerTask(userMapper, saveList, 0, saveList.size()-1, 3000);
    	    	ForkJoinPool fork = new ForkJoinPool(Runtime.getRuntime().availableProcessors() * 2);
    	    	fork.invoke(ht);
    	    	userList.clear();
    		}
    	}
    }
    
}

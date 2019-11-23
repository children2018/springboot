package com.spring.springboot.controller;
import java.util.Date;
import java.util.Hashtable;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.springboot.mapper.UserMapper;
import com.spring.springboot.model.User;
 
@Controller
public class TestController {
	
	@Autowired
	private UserMapper userMapper;
 
	@RequestMapping("/demo")
	@ResponseBody
	public String demo() {
		return "Hello World!";
	}
	
	@GetMapping("/ok")
	@ResponseBody
	public User ok() {
		User user = new User();
		user.setId(UUID.randomUUID().toString());
		user.setName("testjack");
		user.setPassword("DSKLFKLDSJFLDSFDSFD");
		user.setAge(18);
		userMapper.insert(user);
		return user;
	}
	
	@GetMapping("/supervene")
	@ResponseBody
	public User supervene() {
		Hashtable<Integer, Integer> log = new Hashtable<Integer, Integer>();
		log.put(1,0);
		log.put(0,0);
		User user = new User();
		user.setId("1d8bf18d-9dcf-4d6d-b62e-ec740d4b2297");
		user.setName("testjack");
		user.setPassword("DSKLFKLDSJFLDSFDSFD");
		user.setAge(19);
		user.setRevision("1");
		CountDownLatch cdl = new CountDownLatch(1000);
		Long start = new Date(). getTime();
		ExecutorService execute = Executors.newFixedThreadPool(1000);
		for (int index =1 ;index <= 1000;index++) {
			execute.submit(new Thread() {
				@Override
				public void run() {
					int result = userMapper.updateUser(user);
					add(log, result);
					System.out.println("-----------------------result:" + result);
					cdl.countDown();
				}
			});
		}
		try {
			cdl.await();
			execute.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Long end = new Date(). getTime();
		System.out.println("0.value:" + log.get(0));
		System.out.println("1.value:" + log.get(1));
		System.out.println("time cost:" + (end-start));
		return user;
	}
	
	private synchronized void add(Hashtable<Integer, Integer> log, int result) {
		log.put(result, log.get(result) + 1);
	}
 
}

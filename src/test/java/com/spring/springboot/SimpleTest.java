package com.spring.springboot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.springboot.mapper.UserMapper;
import com.spring.springboot.model.User;
import com.spring.springboot.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 总量10万的数量，一笔一笔查询耗时50秒
	 * @throws Exception
	 */
	@Test
	public void doTest() throws Exception {
		List<User> list = userMapper.findUserInfo();
		long start = System.currentTimeMillis();
		long sum = 0 ;
		for (User user : list) {
			System.out.println(++sum);
			User userr = userMapper.queryUserInfoById(user.getId());
			if (userr == null || StringUtils.isEmpty(userr.getName())) {
				throw new Exception("查询失败");
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("cost:" + (end - start));
		System.out.println("it's ok");
	}
	
	/**
	 * 总量10万的数量，1000笔为一个批次查询耗时2秒，实际是1.6秒
	 * @throws Exception
	 */
	@Test
	public void testIds() throws Exception {
		List<User> list = userMapper.findUserInfo();
		long start = System.currentTimeMillis();
		long sum = 0 ;
		List<User> queryList = new ArrayList<User>();
		for (User user : list) {
			queryList.add(user);
			System.out.println(++sum);
			if (queryList.size() % 1000 == 0) {
				List<String> sss = queryList.stream().map(o -> o.getId()).collect(Collectors.toList());
				List<User> userList = userMapper.queryUserInfoByIds("'" + StringUtils.join(sss, "','") + "'");
				if (userList.stream().anyMatch(o -> StringUtils.isEmpty(o.getName())) || userList.size() != queryList.size()) {
					throw new Exception("查询失败1");
				}
				queryList.clear();
			}
		}
		
		if (queryList.size() > 0) {
			List<User> userList = userMapper.queryUserInfoByIds("'" + StringUtils.join(queryList.stream().map(o -> o.getId()).collect(Collectors.toList()), "','") + "'");
			if (userList.stream().anyMatch(o -> StringUtils.isEmpty(o.getName())) || userList.size() != queryList.size()) {
				throw new Exception("查询失败2");
			}
			queryList.clear();
		}
		long end = System.currentTimeMillis();
		System.out.println("cost:" + (end - start));
		System.out.println("it's ok");
	}
	
}

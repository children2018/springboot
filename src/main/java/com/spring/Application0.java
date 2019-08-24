package com.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.spring.springboot.mapper")
public class Application0 {
	
	public static void main(String[] args) {
		SpringApplication.run(Application0.class, args);
	}
	
}

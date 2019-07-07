package com.spring.springboot.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
@Controller
@RequestMapping("/test")
public class TestController {
 
	@RequestMapping("/demo")
	@ResponseBody
	public String demo() {
		return "Hello World!";
	}
 
}

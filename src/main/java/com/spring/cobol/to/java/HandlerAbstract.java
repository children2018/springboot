package com.spring.cobol.to.java;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class HandlerAbstract implements Handler {
	
	protected String str = null;
	protected List<String> parseArray = null; 
	protected StringBuffer buf = new StringBuffer();
	
	public String proccess() {
		parseArray = Arrays.asList(str.split(" ")).stream().map(
			o -> o.trim().replaceAll("'", "\"").replaceAll("-", "_").replaceAll("SPACES", "null").replaceAll("SPACE", "null").replaceAll(" ", " ")
		).collect(Collectors.toList());
		parseArray.remove(0);
		buf.append("//");
		buf.append(str);
		buf.append("\n");
		handler();
		buf.append("\n");
		return buf.toString();
	}
	
	public abstract void handler();
	
}

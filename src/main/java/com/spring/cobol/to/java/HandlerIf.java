package com.spring.cobol.to.java;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HandlerIf extends HandlerAbstract implements Handler {
	
	public HandlerIf(String str) {
		this.str = str;
	}
	
	//IF B-PRINT-KIND = "1"
	@Override
	public void handler() {
		
		while (parseArray.indexOf("GREATER") >= 0 && parseArray.indexOf("THAN") >= 0) {
			parseArray.set(parseArray.indexOf("GREATER"), ">");
			parseArray.remove(parseArray.indexOf("THAN"));
		}
		
		while (parseArray.indexOf("LESS") >= 0 && parseArray.indexOf("THAN") >= 0) {
			parseArray.set(parseArray.indexOf("LESS"), "<");
			parseArray.remove(parseArray.indexOf("THAN"));
		}
		
		if (parseArray.indexOf("EQUAL") >= 0 && parseArray.indexOf("EQUAL") < parseArray.size() - 1){
			parseArray.set(parseArray.indexOf("EQUAL") + 1, 
				".equals(" + parseArray.get(parseArray.indexOf("EQUAL") + 1) + ")");
			parseArray.remove(parseArray.indexOf("EQUAL"));
		}
		
		int ifIndex = parseArray.indexOf("IF");
		buf.append("if");
		buf.append(" (");
		
		for (int index = ifIndex + 1; index < parseArray.size(); index ++ ){
			buf.append(parseArray.get(index).replaceAll(" AND ", " && ").replaceAll(" OR ", " || "));
			buf.append(" ");
		}
		buf.append(") ");
		buf.append("{");
	}

}

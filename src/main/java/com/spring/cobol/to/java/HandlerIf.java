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

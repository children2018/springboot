package com.spring.cobol.to.java;

public class HandlerMove extends HandlerAbstract implements Handler {
	
	public HandlerMove(String str) {
		this.str = str;
	}
	
	@Override
	public void handler() {
		int moveIndex = parseArray.indexOf("MOVE");
		int toIndex = parseArray.indexOf("TO");
		for (int index = toIndex + 1 ;index < parseArray.size() ; index++ ) {
			buf.append(parseArray.get(index));
			buf.append(" = ");
			buf.append(parseArray.get(moveIndex + 1));
			buf.append(";");
			buf.append("\n");
		}
	}

}

package com.spring.cobol.to.java;
import java.util.concurrent.atomic.AtomicLong;

public class HandlerMethod extends HandlerAbstract implements Handler {
	
	public static AtomicLong atomicLong = new AtomicLong(0);
	
	public HandlerMethod(String str) {
		this.str = str;
	}
	
	//000-OPEN-FILE.
	@Override
	public void handler() {
		if (atomicLong.incrementAndGet() != 1){
			buf.append("}");
			buf.append("\n");
		}
		buf.append("public ");
		buf.append("void ");
		buf.append("METHOD_");
		buf.append(parseArray.get(0).substring(0, parseArray.get(0).indexOf(".")));
		buf.append("()");
		buf.append("{");
	}

}

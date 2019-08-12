package com.spring.aop;

public class DataSourceContextHolder {
	
	private static final ThreadLocal<String> local = new ThreadLocal<String>();

	public static ThreadLocal<String> getLocal() {
		return local;
	}

	/**
	 * 从库可能有多个
	 */
	public static void read() {
		local.set(DataSourceType.READ.getType());
	}

	/**
	 * 主库只有一个
	 */
	public static void write() {
		local.set(DataSourceType.WRITE.getType());
	}

	public static String getJdbcType() {
		return local.get();
	}
}

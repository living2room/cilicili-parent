package com.cilicili.user.shiro.ultra;

public enum LoginType {

	USER("Users"), ADMIN("Admin");

	private String type;

	private LoginType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type.toString();
	}

	/*
	 * public static final String AGENCY = "agency";
	 * 
	 *//** 厂商平台 */
	/*
	 * public static final String FACTORY = "factory";
	 * 
	 *//** 系统平台 */
	/*
	 * public static final String SYSTEM = "system";
	 * 
	 *//** 消费者平台 */
	/*
	 * public static final String PERSON = "person";
	 * 
	 *//** 游客 *//*
				 * public static final String GUEST = "guest"; --------------------- 作者：一夜相思愁
				 * 来源：CSDN 原文：https://blog.csdn.net/visket2008/article/details/78539334
				 * 版权声明：本文为博主原创文章，转载请附上博文链接！
				 */

}

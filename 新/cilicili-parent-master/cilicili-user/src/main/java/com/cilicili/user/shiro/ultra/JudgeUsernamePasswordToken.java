package com.cilicili.user.shiro.ultra;

import org.apache.shiro.authc.UsernamePasswordToken;

public class JudgeUsernamePasswordToken extends UsernamePasswordToken {

	private static final long serialVersionUID = -8669189713484495626L;
	
	//登录类型，判断是普通用户登录，教师登录还是管理员登录
    private String loginType;

    public JudgeUsernamePasswordToken(final String username, final String password, String loginType) {
        super(username,password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return this.loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

}

package com.light.moon.dto;

/**
 * 注册用户信息
 * 
 * @author lihh
 * 
 */
public class RegistUserDto {

	private String loginName;

	private String password;

	private String confirmPassword;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}

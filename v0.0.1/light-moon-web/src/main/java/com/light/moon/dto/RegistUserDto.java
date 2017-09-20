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

	private String validCode;

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

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

}

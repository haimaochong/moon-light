package com.light.moon.dto;

import java.util.Date;

/**
 * 登录用户信息
 * 
 * @author lihh
 * 
 */
public class UserDto {

	private Long userId;

	private String loginName;

	private String userName;

	private Date lastOperateTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getLastOperateTime() {
		return lastOperateTime;
	}

	public void setLastOperateTime(Date lastOperateTime) {
		this.lastOperateTime = lastOperateTime;
	}

}

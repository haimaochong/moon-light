package com.light.moon.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 登陆IP记录实体
 * 
 * @author lihh
 * 
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "MOON_LOGIN_IP")
public class LoginIPEntity extends BaseId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5960281734836607858L;

	private UserInfoEntity user;

	private String ip;

	private Integer loginNum;

	private Date lastLoginTime;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public UserInfoEntity getUser() {
		return user;
	}

	public void setUser(UserInfoEntity user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

}

package com.light.moon.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.light.base.entity.BaseId;
import com.light.moon.enumCode.PayType;

/**
 * 用户实体
 * 
 * @author lihh
 * 
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "MOON_USER_INFO")
public class UserInfoEntity extends BaseId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7578274789825018704L;

	private String loginName;

	private String userName;

	private String password;

	private int sex;

	private String tel;

	private String email;

	private String accountForZfb;

	private String accountForWx;

	private PayType payType;

	private Date registTime;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountForZfb() {
		return accountForZfb;
	}

	public void setAccountForZfb(String accountForZfb) {
		this.accountForZfb = accountForZfb;
	}

	public String getAccountForWx() {
		return accountForWx;
	}

	public void setAccountForWx(String accountForWx) {
		this.accountForWx = accountForWx;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

}

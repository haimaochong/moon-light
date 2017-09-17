package com.light.moon.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	
	private Date birthday;

	private String tel;

	private String email;
	
	private String weixin;
	
	private String qq;

	private String note;

	private String accountForZfbUser;
	
	private String accountForZfb;
	
	private String accountForQq;

	private String accountForWx;

	private String bankAccount;

	private String bankAccountCode;

	private String openBank;

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

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getAccountForQq() {
		return accountForQq;
	}

	public void setAccountForQq(String accountForQq) {
		this.accountForQq = accountForQq;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAccountForZfbUser() {
		return accountForZfbUser;
	}

	public void setAccountForZfbUser(String accountForZfbUser) {
		this.accountForZfbUser = accountForZfbUser;
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

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankAccountCode() {
		return bankAccountCode;
	}

	public void setBankAccountCode(String bankAccountCode) {
		this.bankAccountCode = bankAccountCode;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
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

package com.light.moon.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.light.moon.enumCode.PayType;

/**
 * 用户信息
 * 
 * @author lihh
 * 
 */
public class UserInfoDto {

	private String userName;

	private int sex;

	private Date birthday;

	private String email;

	private String qq;

	private String weixin;

	private String note;

	private PayType payType;

	private String accountForZfbUser;

	private String accountForZfb;

	private String accountForQq;

	private String bankAccount;

	private String bankAccountCode;

	private String openBank;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@DateTimeFormat(pattern="yyyy-MM-dd") 
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

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
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

	public String getAccountForQq() {
		return accountForQq;
	}

	public void setAccountForQq(String accountForQq) {
		this.accountForQq = accountForQq;
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

}

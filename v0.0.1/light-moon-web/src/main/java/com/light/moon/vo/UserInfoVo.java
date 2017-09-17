package com.light.moon.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.light.moon.enumCode.PayType;

/**
 * 前端的数据VO
 * 
 * @author lihh
 * 
 */
public class UserInfoVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9056890496318752805L;

	private String loginName;

	private String userName;

	private int sex;

	private Date birthday;

	private String tel;

	private String email;

	private String qq;

	private String note;

	private String accountForZfbUser;

	private String accountForZfb;

	private String accountForQq;

	private String bankAccount;

	private String bankAccountCode;

	private String openBank;

	private PayType payType;

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

}

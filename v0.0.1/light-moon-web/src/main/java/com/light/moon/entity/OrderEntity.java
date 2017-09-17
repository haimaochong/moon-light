package com.light.moon.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.light.moon.enumCode.InvestRecordStatus;
import com.light.moon.enumCode.PayType;

/**
 * 投资记录实体
 * 
 * @author lihh
 * 
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "MOON_ORDER")
public class OrderEntity extends BaseId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3517628549357289439L;

	private PlatformInfoEntity platForm;

	private UserInfoEntity user;

	private InvestRecordStatus status;

	private Long applyTelephone;

	private String applyUserName;

	private BigDecimal amount;

	private String timeRange;

	private PayType payType;

	private String account;

	private Date investTime;

	private Date createTime;

	private Date returnTime;

	private String note;

	@ManyToOne
	@JoinColumn(name = "PALTFORM_ID")
	public PlatformInfoEntity getPlatForm() {
		return platForm;
	}

	public void setPlatForm(PlatformInfoEntity platForm) {
		this.platForm = platForm;
	}

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	public UserInfoEntity getUser() {
		return user;
	}

	public void setUser(UserInfoEntity user) {
		this.user = user;
	}

	public InvestRecordStatus getStatus() {
		return status;
	}

	public void setStatus(InvestRecordStatus status) {
		this.status = status;
	}

	public Long getApplyTelephone() {
		return applyTelephone;
	}

	public void setApplyTelephone(Long applyTelephone) {
		this.applyTelephone = applyTelephone;
	}

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getInvestTime() {
		return investTime;
	}

	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}

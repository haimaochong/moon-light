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

import com.light.base.entity.BaseId;
import com.light.moon.enumCode.InvestRecordStatus;

/**
 * 投资记录实体
 * 
 * @author lihh
 * 
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "MOON_INVEST")
public class InvestEntity extends BaseId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3517628549357289439L;

	private PlatformInfoEntity platForm;

	private UserInfoEntity user;

	private InvestRecordStatus status;

	private BigDecimal investAmount;

	private Date investTime;

	private Date returnMoneyTime;

	private BigDecimal returnMoney;

	private BigDecimal realReturnMoney;

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

	public BigDecimal getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}

	public Date getInvestTime() {
		return investTime;
	}

	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}

	public Date getReturnMoneyTime() {
		return returnMoneyTime;
	}

	public void setReturnMoneyTime(Date returnMoneyTime) {
		this.returnMoneyTime = returnMoneyTime;
	}

	public BigDecimal getReturnMoney() {
		return returnMoney;
	}

	public void setReturnMoney(BigDecimal returnMoney) {
		this.returnMoney = returnMoney;
	}

	public BigDecimal getRealReturnMoney() {
		return realReturnMoney;
	}

	public void setRealReturnMoney(BigDecimal realReturnMoney) {
		this.realReturnMoney = realReturnMoney;
	}

}

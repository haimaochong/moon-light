package com.light.moon.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.light.moon.enumCode.ReturnMoneyType;

/**
 * 平台实体
 * 
 * @author lihh
 * 
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "MOON_PLATFORM_INFO")
public class PlatFormInfoEntity extends BaseId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1003470562125788029L;

	private String name;

	private String icoUrl;

	private Date createTime;

	private BigDecimal minInvestAccount;

	private BigDecimal maxInvestAccount;

	private String investCycle;

	private ReturnMoneyType type;

	private String desc;

	private List<InvestEntity> invests;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcoUrl() {
		return icoUrl;
	}

	public void setIcoUrl(String icoUrl) {
		this.icoUrl = icoUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getMinInvestAccount() {
		return minInvestAccount;
	}

	public void setMinInvestAccount(BigDecimal minInvestAccount) {
		this.minInvestAccount = minInvestAccount;
	}

	public BigDecimal getMaxInvestAccount() {
		return maxInvestAccount;
	}

	public void setMaxInvestAccount(BigDecimal maxInvestAccount) {
		this.maxInvestAccount = maxInvestAccount;
	}

	public String getInvestCycle() {
		return investCycle;
	}

	public void setInvestCycle(String investCycle) {
		this.investCycle = investCycle;
	}

	public ReturnMoneyType getType() {
		return type;
	}

	public void setType(ReturnMoneyType type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@OneToMany(mappedBy = "platForm", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JsonIgnore
	public List<InvestEntity> getInvests() {
		return invests;
	}

	public void setInvests(List<InvestEntity> invests) {
		this.invests = invests;
	}

}

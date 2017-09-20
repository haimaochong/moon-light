package com.light.moon.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.light.moon.enumCode.PlatformGroup;

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
public class PlatformInfoEntity extends BaseId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1003470562125788029L;

	/**
	 * 平台名称
	 */
	private String name;

	/**
	 * 平台Logo
	 */
	private String icoUrl;

	/**
	 * 期限
	 */
	private Integer deadline;

	/**
	 * 期限类型
	 */
	private String deadlineType;

	/**
	 * 平台分组
	 */
	private String _platformGroups;

	/**
	 * 关键字
	 */
	private String _keywords;

	/**
	 * 返现说明
	 */
	private String returnMoneyDesc;

	/**
	 * 投资说明
	 */
	private String desc;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 投资记录
	 */
	private List<OrderEntity> orders;

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

	public Integer getDeadline() {
		return deadline;
	}

	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}

	public String getDeadlineType() {
		return deadlineType;
	}

	public void setDeadlineType(String deadlineType) {
		this.deadlineType = deadlineType;
	}

	@Deprecated
	@Column(name = "PLATFORM_GROUPS")
	public String get_platformGroups() {
		return _platformGroups;
	}

	@Deprecated
	public void set_platformGroups(String _platformGroups) {
		this._platformGroups = _platformGroups;
	}

	@Transient
	public List<PlatformGroup> getPlatformGroups() {
		List<PlatformGroup> platformGroupList = Lists.newArrayList();
		if (StringUtils.isBlank(_platformGroups)) {
			return platformGroupList;
		}

		PlatformGroup[] platformGroupArr = PlatformGroup.values();
		String[] platformGroupStrs = _platformGroups.split(",");
		for (String platformGroupStr : platformGroupStrs) {
			platformGroupList.add(platformGroupArr[Integer.valueOf(platformGroupStr)]);
		}

		return platformGroupList;
	}

	public void setPlatformGroups(List<PlatformGroup> platformGroups) {
		if (CollectionUtils.isEmpty(platformGroups)) {
			_platformGroups = null;
			return;
		}

		// 只取前三个
		if (platformGroups.size() > 3) {
			platformGroups = platformGroups.subList(0, 3);
		}

		StringBuilder platformGroupStr = new StringBuilder();
		for (PlatformGroup platformGroup : platformGroups) {
			platformGroupStr.append(platformGroup.getCode()).append(",");
		}
		_platformGroups = platformGroupStr.substring(0, platformGroupStr.length() - 1);
	}

	@Deprecated
	@Column(name = "KEYWORDS")
	public String get_keywords() {
		return _keywords;
	}

	@Deprecated
	public void set_keywords(String _keywords) {
		this._keywords = _keywords;
	}

	@Transient
	public List<String> getKeywords() {
		List<String> keywords = Lists.newArrayList();
		if (StringUtils.isBlank(_keywords)) {
			return keywords;
		}

		return Lists.newArrayList(_keywords.split(","));
	}

	public void setKeywords(List<String> keywords) {
		// 只取前三个
		if (keywords.size() > 3) {
			keywords = keywords.subList(0, 3);
		}

		_keywords = StringUtils.join(keywords, ",");
	}

	public String getReturnMoneyDesc() {
		return returnMoneyDesc;
	}

	public void setReturnMoneyDesc(String returnMoneyDesc) {
		this.returnMoneyDesc = returnMoneyDesc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@OneToMany(mappedBy = "platForm", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JsonIgnore
	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

}

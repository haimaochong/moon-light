package com.light.moon.dto;

import com.light.moon.enumCode.PlatformBackground;

/**
 * 平台查询条件
 * 
 * @author lihh
 * 
 */
public class QueryPlatformParams {

	private Long platformId;

	private PlatformBackground background;

	private Boolean isInvestCycle;

	private int pageIndex;

	private int pageSize;

	private String orderBy;

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public PlatformBackground getBackground() {
		return background;
	}

	public void setBackground(PlatformBackground background) {
		this.background = background;
	}

	public Boolean getIsInvestCycle() {
		return isInvestCycle;
	}

	public void setIsInvestCycle(Boolean isInvestCycle) {
		this.isInvestCycle = isInvestCycle;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}

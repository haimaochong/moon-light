package com.light.moon.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.light.base.entity.BaseId;

/**
 * 公告实体
 * 
 * @author lihh
 * 
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "MOON_NOTICE")
public class NoticeEntity extends BaseId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6100891742861199443L;

	private String title;

	private String subtitle;

	private String content;

	private Date createTime;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

package com.light.moon.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 实体基类
 * 
 * @author lihh
 * 
 */
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
public class BaseId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3806911249585431287L;

	protected Long id;// 实体主键

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

package com.light.moon.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 实体积累
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
	@GeneratedValue(generator = "localIdGenerator")
	@GenericGenerator(name = "localIdGenerator", strategy = "com.light.moon.entity.LocalIdGenerator")
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

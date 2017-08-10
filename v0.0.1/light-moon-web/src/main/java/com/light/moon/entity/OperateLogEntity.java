package com.light.moon.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.light.moon.enumCode.OperatorType;

/**
 * 操作日志实体
 * 
 * @author lihh
 * 
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "MOON_OPERATOR_LOG")
public class OperateLogEntity extends BaseId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2426005087216572982L;

	private UserInfoEntity operator;

	private OperatorType type;

	private String msg;

	private Date operateTime;

	@ManyToOne
	@JoinColumn(name = "OPERATOR")
	public UserInfoEntity getOperator() {
		return operator;
	}

	public void setOperator(UserInfoEntity operator) {
		this.operator = operator;
	}

	public OperatorType getType() {
		return type;
	}

	public void setType(OperatorType type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

}

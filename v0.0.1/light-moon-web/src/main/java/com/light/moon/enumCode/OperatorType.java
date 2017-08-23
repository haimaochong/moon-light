package com.light.moon.enumCode;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum OperatorType {

	/**
	 * 用户信息操作
	 **/
	USER_LOGIN(0, "用户登录"),

	/**
	 * 用户信息操作
	 **/
	USER_MSG(1, "用户信息操作"),

	/**
	 * 投资操作
	 **/
	INVEST(2, "投资操作");

	OperatorType(int code, String text) {
		this.code = code;
		this.text = text;
	}

	private int code;
	private String text;

	public String getName() {
		return this.name();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static String getText(int code) {
		for (OperatorType c : OperatorType.values()) {
			if (c.code == code) {
				return c.text;
			}
		}
		return null;
	}

	public static OperatorType forCode(int code) {
		for (OperatorType c : OperatorType.values()) {
			if (c.code == code) {
				return c;
			}
		}
		return null;
	}

	@JsonValue
	public Map<String, Object> getJsonValue() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", this.getCode());
		map.put("name", this.name());
		map.put("text", this.getText());
		return map;
	}

}

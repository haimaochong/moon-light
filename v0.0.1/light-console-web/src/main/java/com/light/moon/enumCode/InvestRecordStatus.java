package com.light.moon.enumCode;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum InvestRecordStatus {

	/**
	 * 返现中
	 **/
	returnning(0, "返现中"),

	/**
	 * 已返现
	 **/
	returned(1, "已返现"),

	/**
	 * 已关闭
	 **/
	closed(2, "已关闭");

	InvestRecordStatus(int code, String text) {
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
		for (InvestRecordStatus c : InvestRecordStatus.values()) {
			if (c.code == code) {
				return c.text;
			}
		}
		return null;
	}

	public static InvestRecordStatus forCode(int code) {
		for (InvestRecordStatus c : InvestRecordStatus.values()) {
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

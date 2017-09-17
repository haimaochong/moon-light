package com.light.moon.enumCode;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum ReturnMoneyType {

	/**
	 * 立返
	 **/
	TODAY(0, "立返"),

	/**
	 * 次返
	 **/
	NEXT_DAY(1, "次返");

	ReturnMoneyType(int code, String text) {
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
		for (ReturnMoneyType c : ReturnMoneyType.values()) {
			if (c.code == code) {
				return c.text;
			}
		}
		return null;
	}

	public static ReturnMoneyType forCode(int code) {
		for (ReturnMoneyType c : ReturnMoneyType.values()) {
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

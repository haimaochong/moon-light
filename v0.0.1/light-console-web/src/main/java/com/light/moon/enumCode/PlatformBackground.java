package com.light.moon.enumCode;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum PlatformBackground {

	/**
	 * 国企背景
	 **/
	GZBG(0, "国企背景"),

	/**
	 * 民营系
	 **/
	MYX(1, "民营系"),

	/**
	 * 有风投
	 **/
	YFT(2, "有风投"),

	/**
	 * 上市背景
	 **/
	SSBJ(3, "上市背景"),

	/**
	 * 其他
	 **/
	QT(4, "其他");

	PlatformBackground(int code, String text) {
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
		for (PlatformBackground c : PlatformBackground.values()) {
			if (c.code == code) {
				return c.text;
			}
		}
		return null;
	}

	public static PlatformBackground forCode(int code) {
		for (PlatformBackground c : PlatformBackground.values()) {
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

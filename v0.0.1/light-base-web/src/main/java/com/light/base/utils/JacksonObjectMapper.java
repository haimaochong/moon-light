package com.light.base.utils;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author lihh
 *
 */
public class JacksonObjectMapper extends ObjectMapper {

	/**
	 *
	 */
	private static final long serialVersionUID = 6773224424978770429L;

	public JacksonObjectMapper() {
		super();
		this.setTimeZone(TimeZone.getTimeZone("GMT+8")); // 设置为中国上海时区
		this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
		this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);

		this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		this.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
	}

}

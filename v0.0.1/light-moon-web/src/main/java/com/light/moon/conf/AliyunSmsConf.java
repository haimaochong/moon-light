package com.light.moon.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 阿里云短信服务配置项
 * 
 * @author lihh
 *
 */
@Component
public class AliyunSmsConf {

	@Value("${sms.serverUrl}")
	private String serverUrl;

	@Value("${sms.accessKeyId}")
	private String accessKeyId;

	@Value("${sms.accessKeySecret}")
	private String accessKeySecret;

	@Value("${sms.template.regist}")
	private String template_regist;

	@Value("${sms.template.resetPwd}")
	private String template_resetPwd;

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getTemplate_regist() {
		return template_regist;
	}

	public void setTemplate_regist(String template_regist) {
		this.template_regist = template_regist;
	}

	public String getTemplate_resetPwd() {
		return template_resetPwd;
	}

	public void setTemplate_resetPwd(String template_resetPwd) {
		this.template_resetPwd = template_resetPwd;
	}

}

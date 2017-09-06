package com.light.moon.message;

import org.springframework.beans.factory.InitializingBean;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public abstract class AbsSms implements InitializingBean {

	protected SendSmsRequest request = new SendSmsRequest();

	protected IAcsClient acsClient = null;

	public enum TemplateCode {
		/**
		 * 用户信息操作
		 **/
		regist("SMS_89625043"),

		/**
		 * 用户信息操作
		 **/
		reset_PWD("SMS_89605049");

		private String templateCode;

		TemplateCode(String templateCode) {
			this.templateCode = templateCode;
		}

		public String getTemplateCode() {
			return templateCode;
		}

		public void setTemplateCode(String templateCode) {
			this.templateCode = templateCode;
		}

	}

	public abstract void sendAuthCode(String phoneNum, TemplateCode code,
			String userName, Integer authCode);

	@Override
	public void afterPropertiesSet() throws Exception {
		// 设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "30");
		System.setProperty("sun.net.client.defaultReadTimeout", "120");

		// 初始化ascClient需要的几个参数
		final String product = "Dysmsapi"; // 短信API产品名称（短信产品名固定，无需修改）
		final String domain = "dysmsapi.aliyuncs.com"; // 短信API产品域名（接口地址固定，无需修改）
		// 替换成你的AK
		final String accessKeyId = "LTAIFRti0dEk791G"; // 你的accessKeyId,参考本文档步骤2
		final String accessKeySecret = "6BwAaEjakUf9RPauVx9K1uyTvo9MhP"; // 你的accessKeySecret，参考本文档步骤2

		// 初始化ascClient,暂时不支持多region
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
				accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product,
				domain);

		acsClient = new DefaultAcsClient(profile);
		// 使用post提交
		request.setMethod(MethodType.POST);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("勇闯科技");
	}

}

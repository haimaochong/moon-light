package com.light.moon.message;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.google.common.collect.Maps;
import com.light.moon.conf.AliyunSmsConf;
import com.light.moon.enumCode.OperatorType;
import com.light.moon.exception.ServiceException;
import com.light.moon.service.OperatorLogService;
import com.light.moon.utils.RandomUtils;

/**
 * 阿里云短信服务
 * 
 * @author lihh
 * 
 */
@Component
public class AliyunSms implements Sms, InitializingBean {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final int VALID_CODE_TIME_OUT = 5 * 60; // 验证码超时时间

	private static Map<String, ValidCodeCacheValue> validCodeMap = Maps.newHashMap();

	protected IAcsClient acsClient = null;

	@Resource
	private AliyunSmsConf smsConf;

	@Resource
	private OperatorLogService operatorLogService;

	@Override
	public void sendRegistAuthCode(Long phoneNum) {
		String template = smsConf.getTemplate_regist();
		String authCode = RandomUtils.getRandomNum(6);

		sendAuthCode(phoneNum, template, authCode);
		operatorLogService.addOperatorLog(phoneNum.toString(), OperatorType.USER_MSG, "发送注册验证码成功！验证码：" + authCode);
	}

	@Override
	public void sendResetPwdAuthCode(Long phoneNum) {
		String template = smsConf.getTemplate_resetPwd();
		String authCode = RandomUtils.getRandomNum(6);

		sendAuthCode(phoneNum, template, authCode);
		operatorLogService.addOperatorLog(phoneNum.toString(), OperatorType.USER_MSG, "发送密码重置验证码成功！验证码：" + authCode);
	}

	@Override
	public boolean validRegistAuthCode(Long phoneNum, String validCode) {
		String template = smsConf.getTemplate_regist();
		return valid(phoneNum, template, validCode);
	}

	@Override
	public boolean validResetPwdAuthCode(Long phoneNum, String validCode) {
		String template = smsConf.getTemplate_resetPwd();
		return valid(phoneNum, template, validCode);
	}

	private boolean valid(Long phoneNum, String template, String validCode) {
		String cacheKey = buildCacheKey(phoneNum, template);
		ValidCodeCacheValue value = validCodeMap.get(cacheKey);
		if (null == value) {
			throw new ServiceException("未发送验证码，请重新发送！");
		}

		if (new Date().getTime() - value.getSendTime().getTime() > VALID_CODE_TIME_OUT * 1000) {
			validCodeMap.remove(cacheKey);
			throw new ServiceException("验证码已过期，请重新发送！");
		}

		if (!value.getValidCode().equals(validCode)) {
			throw new ServiceException("验证码错误，请重新输入！");
		}

		validCodeMap.remove(cacheKey);
		return true;
	}

	private void sendAuthCode(Long phoneNum, String template, String authCode) {
		String cacheKey = buildCacheKey(phoneNum, template);

		try {
			send(phoneNum, template, authCode);
			validCodeMap.put(cacheKey, new ValidCodeCacheValue(authCode));
		} catch (Exception e) {
			logger.error("短信发送失败！", e);
			throw new ServiceException("系统异常");
		}
	}

	private String buildCacheKey(Long phoneNum, String template) {
		return new StringBuilder().append(phoneNum).append("_").append(template).toString();
	}

	private void send(Long phoneNum, String templateCode, String authCode) throws ServiceException {
		SendSmsRequest request = getRequest();
		request.setPhoneNumbers(phoneNum.toString());
		request.setTemplateCode(templateCode);
		request.setTemplateParam("{\"code\":\"" + authCode + "\"}");

		// 请求失败这里会抛ClientException异常
		try {
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			if (sendSmsResponse.getCode() == null || !sendSmsResponse.getCode().equals("OK")) {
				throw new ServiceException("短信发送发生异常！原因：" + sendSmsResponse.getMessage());
			}
		} catch (Exception e) {
			throw new ServiceException("短信发送失败！", e);
		}
	}

	private SendSmsRequest getRequest() {
		// 组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		// 使用post提交
		request.setMethod(MethodType.POST);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName("安心返利");

		return request;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsConf.getAccessKeyId(),
				smsConf.getAccessKeySecret());
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", smsConf.getServerUrl());
		acsClient = new DefaultAcsClient(profile);
	}

	class ValidCodeCacheValue {

		private String validCode;

		private Date sendTime;

		public ValidCodeCacheValue(String validCode) {
			this.validCode = validCode;
			this.sendTime = new Date();
		}

		public String getValidCode() {
			return validCode;
		}

		public void setValidCode(String validCode) {
			this.validCode = validCode;
		}

		public Date getSendTime() {
			return sendTime;
		}

		public void setSendTime(Date sendTime) {
			this.sendTime = sendTime;
		}

	}

}

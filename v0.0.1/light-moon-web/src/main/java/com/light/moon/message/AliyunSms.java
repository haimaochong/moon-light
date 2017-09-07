package com.light.moon.message;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.light.moon.conf.AliyunSmsConf;
import com.light.moon.exception.ServiceException;

/**
 * 阿里云短信服务
 * 
 * @author lihh
 *
 */
@Component
public class AliyunSms implements Sms, InitializingBean {

	protected IAcsClient acsClient = null;

	@Resource
	private AliyunSmsConf smsConf;

	@Override
	public void sendRegistAuthCode(String phoneNum, Integer authCode) {
		sendAuthCode(phoneNum, smsConf.getTemplate_regist(), authCode);
	}

	@Override
	public void sendResetPwdAuthCode(String phoneNum, Integer authCode) {
		sendAuthCode(phoneNum, smsConf.getTemplate_resetPwd(), authCode);
	}

	private void sendAuthCode(String phoneNum, String templateCode, Integer authCode) throws ServiceException {
		SendSmsRequest request = getRequest();
		request.setPhoneNumbers(phoneNum);
		request.setTemplateCode(templateCode);
		request.setTemplateParam("{\"code\":\"" + authCode + "\"}");

		// 请求失败这里会抛ClientException异常
		try {
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			if (sendSmsResponse.getCode() == null || !sendSmsResponse.getCode().equals("OK")) {
				throw new ServiceException("短信发送发生异常！");
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

}

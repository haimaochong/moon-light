package com.light.moon.message;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.light.moon.exception.ServiceException;

public class Sms extends AbsSms {

	@Override
	public void sendAuthCode(String phoneNum, TemplateCode code, String userName, Integer authCode)
			throws ServiceException {
		request.setPhoneNumbers(phoneNum);
		request.setTemplateCode(code.getTemplateCode());
		request.setTemplateParam("{\"name\":\"" + userName + "\", \"code\":\"" + authCode + "\"}");

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

}

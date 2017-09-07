package com.light.moon.message;

public interface Sms {

	public void sendRegistAuthCode(String phoneNum, Integer authCode);

	public void sendResetPwdAuthCode(String phoneNum, Integer authCode);

}

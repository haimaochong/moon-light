package com.light.moon.message;

public interface Sms {

	public void sendRegistAuthCode(Long phoneNum);

	public void sendResetPwdAuthCode(Long phoneNum);

	public boolean validRegistAuthCode(Long phoneNum, String validCode);

	public boolean validResetPwdAuthCode(Long phoneNum, String validCode);

}

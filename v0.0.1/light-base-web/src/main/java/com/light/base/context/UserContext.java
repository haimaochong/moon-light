package com.light.base.context;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.light.base.dto.UserDto;
import com.light.base.filter.UserInfoFilter;

public class UserContext {

	private static Logger logger = LoggerFactory.getLogger(UserInfoFilter.class);

	public static final long LOGIN_TIME_OUT = 30 * 60 * 1000;

	private static Map<String, UserDto> loginUserMap = Maps.newHashMap();

	private static UserContext instance = new UserContext();

	private UserContext() {
	}

	static {
		checkLoginUser();
	}

	public static UserContext getInstance() {
		return instance;
	}

	public UserDto getLoginUser(String sessionId) {
		if (loginUserMap.containsKey(sessionId)) {
			return loginUserMap.get(sessionId);
		}
		return null;
	}

	public Boolean addLoginUser(String sessionId, UserDto userDto) {
		loginUserMap.put(sessionId, userDto);
		return true;
	}

	public UserDto removeLoginUser(String sessionId) {
		if (loginUserMap.containsKey(sessionId)) {
			UserDto userDto = loginUserMap.get(sessionId);
			loginUserMap.remove(sessionId);
			ThreadLocalInfo.getInstance().setUser(null);
			ThreadLocalInfo.getInstance().getRequest().getSession().removeAttribute("loginName");
			return userDto;
		}
		return null;
	}

	public Boolean checkLogin(String sessionId) {
		UserDto user = loginUserMap.get(sessionId);
		if (null == user || new Date().getTime() - user.getLastOperateTime().getTime() > LOGIN_TIME_OUT) {
			loginUserMap.remove(sessionId);
			return false;
		}

		return true;
	}

	private static void checkLoginUser() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Set<String> loginTimeOutKeySet = Sets.newHashSet();

				Set<String> keySet = loginUserMap.keySet();
				for (String key : keySet) {
					UserDto user = loginUserMap.get(key);
					if (null == user || new Date().getTime() - user.getLastOperateTime().getTime() > LOGIN_TIME_OUT) {
						loginTimeOutKeySet.add(key);
					}
				}

				if (!CollectionUtils.isEmpty(loginTimeOutKeySet)) {
					for (String key : loginTimeOutKeySet) {
						loginUserMap.remove(key);
					}
				}

				try {
					Thread.sleep(5 * 60 * 1000);
				} catch (InterruptedException e) {
					logger.error("校验登录超时用户，线程休眠发生异常！", e);
				}
			}

		}).start();
	}

}

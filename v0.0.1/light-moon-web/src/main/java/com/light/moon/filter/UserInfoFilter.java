package com.light.moon.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.light.moon.dto.UserDto;
import com.light.moon.entity.UserInfoEntity;
import com.light.moon.service.UserInfoService;
import com.light.moon.utils.SpringContextHolder;
import com.light.moon.utils.ThreadLocalInfo;

/**
 * 用户登录拦截器
 * 
 * @author lihh
 *
 */
public class UserInfoFilter extends OncePerRequestFilter {

	private static Logger logger = LoggerFactory.getLogger(UserInfoFilter.class);

	private static final long LOGIN_TIME_OUT = 30 * 60 * 1000;

	private static Map<String, UserDto> loginUserMap = Maps.newHashMap();

	static {
		checkLoginUser();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String excludeExp = getFilterConfig().getInitParameter("excludeExp");
		String reqUri = request.getRequestURI();

		if (StringUtils.isBlank(excludeExp) || !reqUri.matches(excludeExp)) {
			requestBefore(request, response);
		}
		
		filterChain.doFilter(request, response);
		requestAfter(request, response);
	}

	private void requestBefore(HttpServletRequest request, HttpServletResponse response) {
		String reqUri = request.getRequestURI();
		String sessionId = getSessionId(request, response);
		
		if(reqUri.matches(".*login/login$")) {
			login(sessionId, request);
		}

		UserDto user = updateLoginUser(sessionId, request);

		ThreadLocalInfo threadLocalInfo = ThreadLocalInfo.getInstance();
		threadLocalInfo.setRequest(request);
		threadLocalInfo.setResponse(response);
		threadLocalInfo.setUser(user);
	}

	private void requestAfter(HttpServletRequest request, HttpServletResponse response) {
		ThreadLocalInfo threadLocalInfo = ThreadLocalInfo.getInstance();
		UserDto user = threadLocalInfo.getUser();
		if (null != user) {
			request.setAttribute("loginName", user.getUserName());
		}
	}
	
	private void login(String sessionId, HttpServletRequest request) {
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		UserInfoService userInfoService = SpringContextHolder.getBean(UserInfoService.class);
		UserInfoEntity user = userInfoService.queryUser(loginName, password);
		
		if(null != user) {
			UserDto userDto = new UserDto();
			userDto.setUserId(user.getId());
			userDto.setLoginName(user.getLoginName());
			userDto.setUserName(user.getUserName());
			userDto.setLastOperateTime(new Date());
			loginUserMap.put(sessionId, userDto);
		}
	}

	private String getSessionId(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if ("JSESSIONID".equals(cookie.getName().toUpperCase())) {
					return cookie.getValue();
				}
			}
		}

		HttpSession session = request.getSession(true);
		String sessionId = session.getId();

		Cookie cookie = new Cookie("JSESSIONID", sessionId);
		cookie.setPath(request.getContextPath());
		cookie.setHttpOnly(true);
		cookie.setMaxAge(30 * 60);
		response.addCookie(cookie);

		return sessionId;
	}

	private UserDto updateLoginUser(String sessionId, HttpServletRequest request) {
		if (null != sessionId) {
			UserDto userDto = loginUserMap.get(sessionId);
			if (null != userDto) {
				if (new Date().getTime() - userDto.getLastOperateTime().getTime() < LOGIN_TIME_OUT) {
					userDto.setLastOperateTime(new Date());
					loginUserMap.put(sessionId, userDto);
					return userDto;
				} else {
					loginUserMap.remove(sessionId);
				}
			}
		}

		return null;
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

package com.light.base.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.light.base.context.ThreadLocalInfo;
import com.light.base.context.UserContext;
import com.light.base.dto.UserDto;

/**
 * 用户登录拦截器
 * 
 * @author lihh
 * 
 */
public class UserInfoFilter extends OncePerRequestFilter {

	private UserContext userContext = UserContext.getInstance();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String excludeExp = getFilterConfig().getInitParameter("excludeExp");
		String reqUri = request.getRequestURI();

		if (StringUtils.isBlank(excludeExp) || !reqUri.matches(excludeExp)) {
			requestBefore(request, response);
		}

		filterChain.doFilter(request, response);

		if (StringUtils.isBlank(excludeExp) || !reqUri.matches(excludeExp)) {
			requestAfter(request, response);
		}
	}

	private void requestBefore(HttpServletRequest request, HttpServletResponse response) {
		String sessionId = getSessionId(request, response);

		UserDto user = updateLoginUser(sessionId, request);

		ThreadLocalInfo threadLocalInfo = ThreadLocalInfo.getInstance();
		threadLocalInfo.setRequest(request);
		threadLocalInfo.setResponse(response);
		threadLocalInfo.setSessionId(sessionId);
		threadLocalInfo.setUser(user);
	}

	private void requestAfter(HttpServletRequest request, HttpServletResponse response) {
		ThreadLocalInfo threadLocalInfo = ThreadLocalInfo.getInstance();
		UserDto user = threadLocalInfo.getUser();
		if (null != user) {
			request.getSession().setAttribute("loginName", user.getUserName());
		} else {
			request.getSession().removeAttribute("loginName");
		}
	}

	private String getSessionId(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if ("JSESSIONID".equals(cookie.getName().toUpperCase())) {
					if(cookie.getMaxAge() < 0) {
						break;
					}
					cookie.setMaxAge(30 * 60);
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
			UserDto userDto = userContext.getLoginUser(sessionId);
			if (null != userDto) {
				if (new Date().getTime() - userDto.getLastOperateTime().getTime() < UserContext.LOGIN_TIME_OUT) {
					userDto.setLastOperateTime(new Date());
					userContext.addLoginUser(sessionId, userDto);
					return userDto;
				} else {
					userContext.removeLoginUser(sessionId);
				}
			}
		}

		return null;
	}

}

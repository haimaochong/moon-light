package com.light.base.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.light.base.dto.UserDto;

public class ThreadLocalInfo {

	private final ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<HttpServletRequest>();

	private final ThreadLocal<HttpServletResponse> currentResponse = new ThreadLocal<HttpServletResponse>();

	private final ThreadLocal<String> currentSessionId = new ThreadLocal<String>();

	private final ThreadLocal<UserDto> currentUser = new ThreadLocal<UserDto>();

	private static ThreadLocalInfo instance = new ThreadLocalInfo();

	private ThreadLocalInfo() {
	}

	public static ThreadLocalInfo getInstance() {
		return instance;
	}

	public HttpServletRequest getRequest() {
		return (HttpServletRequest) this.currentRequest.get();
	}

	public void setRequest(HttpServletRequest request) {
		this.currentRequest.set(request);
	}

	public HttpServletResponse getResponse() {
		return (HttpServletResponse) this.currentResponse.get();
	}

	public void setResponse(HttpServletResponse response) {
		this.currentResponse.set(response);
	}

	public String getSessionId() {
		return (String) this.currentSessionId.get();
	}

	public void setSessionId(String currentSessionId) {
		this.currentSessionId.set(currentSessionId);
	}

	public UserDto getUser() {
		return (UserDto) this.currentUser.get();
	}

	public void setUser(UserDto user) {
		this.currentUser.set(user);
	}

}

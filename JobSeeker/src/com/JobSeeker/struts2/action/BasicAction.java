package com.JobSeeker.struts2.action;

import com.opensymphony.xwork2.*;
import common.UserInfo;
import java.util.*;
import javax.servlet.http.*;
import com.JobSeeker.service.ServiceManager;

public class BasicAction extends ActionSupport implements
		org.apache.struts2.interceptor.ServletRequestAware,
		org.apache.struts2.interceptor.ServletResponseAware {
	protected ServiceManager serviceManager;
	protected UserInfo userInfo;
	protected String result;
	protected Map<String, String> cookies;
	protected javax.servlet.http.HttpServletResponse response;
	protected javax.servlet.http.HttpServletRequest request;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	protected String getCookieValue(String name) {
		javax.servlet.http.Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {

				if (!cookie.getName().equals(name))
					continue;
				return cookie.getValue();
			}

		}
		return null;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

		userInfo.setCookieUser(getCookieValue("user"));
		userInfo.setUserRoot(userInfo.getRoot() + userInfo.getCookieUser());
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;

	}

	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;

	}

	protected void saveCookie(String name, String value, int maxAge) {
		javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(name,
				value);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
}

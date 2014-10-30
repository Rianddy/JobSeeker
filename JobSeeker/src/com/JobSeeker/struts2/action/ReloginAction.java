package com.JobSeeker.struts2.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import common.Constants;
import common.CookieUtil;
public class ReloginAction extends BasicAction {

	@Override
	public String execute() throws Exception {
		try {
			HttpSession session = request.getSession();
			session.setAttribute(Constants.SESSION_USER, null);
			Cookie cookie = new Cookie(Constants.SESSION_USER, null);
			cookie.setMaxAge(0);
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
			return SUCCESS;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
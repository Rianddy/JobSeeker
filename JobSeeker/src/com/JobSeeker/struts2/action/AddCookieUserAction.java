package com.JobSeeker.struts2.action;

import java.util.Map;
import java.util.UUID;

import com.JobSeeker.service.UserService;
import com.JobSeeker.model.Users;
import com.opensymphony.xwork2.ActionContext;

import common.Constants;
public class AddCookieUserAction extends BasicAction{
	
	private String uId=UUID.randomUUID().toString();
	private String prePage;
	
	public String getPrePage() {
		return prePage;
	}

	public void setPrePage(String prePage) {
		this.prePage = prePage;
	}

	@Override
	public String execute() throws Exception{
		try {
			ActionContext ctx = ActionContext.getContext();
			Map<String, Object> session = ctx.getSession();
			prePage = (String) session.get(Constants.BROSER_PRE_PAGE);
			
			Users cookieUser=new Users();
			cookieUser.setUsername(uId);
			cookieUser.setOfficial("False"); 
			cookieUser.setEmail("");
			cookieUser.setPasswordMD5("");
			UserService userService = serviceManager.getUserService();
			userService.addCookieUser(cookieUser);
			saveCookie(Constants.SESSION_USER, cookieUser.getUsername(), Constants.BROWSER_COOKIE_MAX_AGE);
			saveCookie(Constants.OFFICIAL_STATUS, "False", Constants.BROWSER_COOKIE_MAX_AGE);
			if(null == prePage){
				return SUCCESS;
			}else{
				return SUCCESS;
			}
			
		} catch (Exception e) { 
			System.out.println(e);
		}
		return ERROR;
	}
}

package com.JobSeeker.struts2.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.JobSeeker.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.JobSeeker.model.Users;

import common.Constants;
public class LoginAction extends BasicAction implements ModelDriven{
	private Users user = new Users();
	private String prePage;
	@Override
	public Users getModel() {
		return user;
	}
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getPrePage() {
		return prePage;
	}

	public void setPrePage(String prePage) {
		this.prePage = prePage;
	}

	@Override
	public void validate() {
		if ("".equals(user.getValidationCode()))
			return;
		Object obj = ActionContext.getContext().getSession()
				.get("validation_code");

		String validationCode = (obj != null) ? obj.toString() : "";

		if (!validationCode.equalsIgnoreCase(user.getValidationCode())) {
			if (user.getValidationCode() != null) {
				this.addFieldError("validationCode", "The validation code was wrong!");
			}
		}
	}
	@Override
	public String execute() throws Exception{
		try {
			ActionContext ctx = ActionContext.getContext();
			Map<String, Object> session = ctx.getSession();
			prePage = (String) session.get(Constants.BROSER_PRE_PAGE);
			session.remove(Constants.BROSER_PRE_PAGE);
			UserService userService = serviceManager.getUserService();

			if (userService.verifyUser(user)) {
				saveCookie(Constants.SESSION_USER, user.getUsername(), Constants.BROWSER_COOKIE_MAX_AGE);
				HttpSession session1 = request.getSession();
				session1.setAttribute(Constants.SESSION_USER, user.getUsername());
				session1.setMaxInactiveInterval(Constants.INACTIVE_INTERVAL);
				return SUCCESS;
			}
		} catch (Exception e) {
		}
		return ERROR;
	}
}

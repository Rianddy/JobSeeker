package com.JobSeeker.struts2.action;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.JobSeeker.service.JobService;
import com.JobSeeker.service.UserService;
import com.JobSeeker.service.UsersAndJobsService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.JobSeeker.model.Connection;
import com.JobSeeker.model.Jobs;
import com.JobSeeker.model.Users;

import common.Constants;
import common.CookieUtil;

public class RegisterAction extends BasicAction implements ModelDriven<Users> {
	private Users user = new Users();
	private String prePage;
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
	public Users getModel() {
		return user;
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
				this.addFieldError("validationCode",
						"The validation code was wrong!");
			}
		}
	}

	@Override
	public String execute() throws Exception {
		try {
			ActionContext ctx = ActionContext.getContext();
			Map<String, Object> session = ctx.getSession();
			prePage = (String) session.get(Constants.BROSER_PRE_PAGE);
			session.remove(Constants.BROSER_PRE_PAGE); 
			String cookieUsername = CookieUtil.getCookie(request, Constants.SESSION_USER);
			Users cookieUser=new Users();
			cookieUser.setUsername(cookieUsername);
			cookieUser.setPasswordMD5("");
			cookieUser.setEmail("");
			cookieUser.setOfficial(Constants.NOTOFFICIAL);
			user.setOfficial("True"); // Official account
			UserService userService = serviceManager.getUserService();
			JobService jobService = serviceManager.getJobService();
			UsersAndJobsService usersAndJobsService = serviceManager.getUsersAndJobsService();
			List<Jobs> jobs=jobService.getCartJobs(cookieUser);
			userService.addUser(user);
			for(int i=0;i<jobs.size();i++){
				String uId=UUID.randomUUID().toString();
				Connection connection=new Connection();
				connection.setUsername(user.getUsername());
				connection.setJobid(jobs.get(i).getJobid());
				connection.setConnectionstatus(Constants.CART_STATUS);
				connection.setConnectionid(uId);
				usersAndJobsService.saveJobToCart(connection);
			}
			for(int i=0;i<jobs.size();i++){
				Connection connection=new Connection();
				connection.setUsername(cookieUsername);
				connection.setJobid(jobs.get(i).getJobid());
				usersAndJobsService.deleteJobInCart(connection);
			}
			userService.deleteCookieUser(cookieUser);
			File dir = new File(userInfo.getRoot() + user.getUsername());
			if (!dir.exists())
				dir.mkdir();
			saveCookie(Constants.SESSION_USER, user.getUsername(), Constants.BROWSER_COOKIE_MAX_AGE);
			HttpSession session1 = request.getSession();
			session1.setAttribute(Constants.SESSION_USER, user.getUsername());
			session1.setMaxInactiveInterval(Constants.INACTIVE_INTERVAL);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}

	}
}

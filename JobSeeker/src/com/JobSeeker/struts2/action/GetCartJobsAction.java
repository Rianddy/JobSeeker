package com.JobSeeker.struts2.action;

import com.JobSeeker.model.*;
import com.JobSeeker.service.JobService;
import common.Constants;
import common.CookieUtil;

import java.util.List;
public class GetCartJobsAction extends BasicAction{
	private Users user = new Users();
	private List<Jobs> jobs;
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public List<Jobs> getJobs(){
		return jobs;
	}
	public String execute() throws Exception{
		try {
			JobService jobService = serviceManager.getJobService();
			String username = CookieUtil.getCookie(request, Constants.SESSION_USER);
			user.setUsername(username);
			jobs=jobService.getCartJobs(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}
}

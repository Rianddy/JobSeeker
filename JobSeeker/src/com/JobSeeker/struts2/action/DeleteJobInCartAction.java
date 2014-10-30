package com.JobSeeker.struts2.action;

import com.JobSeeker.model.Connection;
import com.JobSeeker.service.UsersAndJobsService;
import common.Constants;
import common.CookieUtil;

public class DeleteJobInCartAction extends BasicAction{
	String jobid;
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public String execute() throws Exception {
		try {
			UsersAndJobsService usersAndJobsService = serviceManager.getUsersAndJobsService();
			Connection usersAndJobs=new Connection();
			String username = CookieUtil.getCookie(request, Constants.SESSION_USER);
			usersAndJobs.setUsername(username);
			usersAndJobs.setJobid(jobid);
			usersAndJobsService.deleteJobInCart(usersAndJobs);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}
}

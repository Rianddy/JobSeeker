package com.JobSeeker.struts2.action;

import com.JobSeeker.model.*;
import com.JobSeeker.service.UsersAndJobsService;
import common.Constants;
import common.CookieUtil;

public class SaveJobToCartAction extends BasicAction{
	private Connection usersAndJobs=new Connection();
	private String jobid;
	public String getJobid() {
		return jobid;
	}
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	public Connection getUsersAndJobs() {
		return usersAndJobs;
	}
	public void setUsersAndJobs(Connection usersAndJobs) {
		this.usersAndJobs = usersAndJobs;
	}
	public String execute() throws Exception{
		try {
			UsersAndJobsService usersAndJobsService = serviceManager.getUsersAndJobsService();
			String username = CookieUtil.getCookie(request, Constants.SESSION_USER);
			usersAndJobs.setUsername(username);
			usersAndJobs.setJobid(jobid);
			if(usersAndJobsService.saveJobToCart(usersAndJobs))
				return SUCCESS;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ERROR;
	}
}

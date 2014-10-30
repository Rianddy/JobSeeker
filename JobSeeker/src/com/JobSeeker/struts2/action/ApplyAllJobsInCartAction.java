package com.JobSeeker.struts2.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.JobSeeker.model.*;
import com.JobSeeker.service.UsersAndJobsService;

import common.Constants;

public class ApplyAllJobsInCartAction extends BasicAction{
	private List<String> jobs=new ArrayList<String>();
	private List<Connection> usersAndJobsList=new ArrayList<Connection>();
	public List<String> getJobs() {
		return jobs;
	}
	public void setJobs(List<String> jobs) {
		this.jobs = jobs;
	}
	public List<Connection> getUsersAndJobsList() {
		return usersAndJobsList;
	}
	public void setUsersAndJobsList(List<Connection> usersAndJobsList) {
		this.usersAndJobsList = usersAndJobsList;
	}
	public String execute() throws Exception{
		try {
			HttpSession session = request.getSession();
			String username=(String)session.getAttribute(Constants.SESSION_USER);
			for(int i=0;i<jobs.size();i++){
				Connection usersAndJobs=new Connection();
				usersAndJobs.setJobid(jobs.get(i));
				usersAndJobs.setUsername(username);
				usersAndJobsList.add(usersAndJobs);
			}
			UsersAndJobsService usersAndJobsService = serviceManager.getUsersAndJobsService();
			if(usersAndJobsService.applyJobsInCart(usersAndJobsList))
				return SUCCESS;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ERROR;
	}
}

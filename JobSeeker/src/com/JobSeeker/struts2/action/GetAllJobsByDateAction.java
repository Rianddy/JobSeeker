package com.JobSeeker.struts2.action;

import java.util.List;

import com.JobSeeker.model.Jobs;
import com.JobSeeker.service.JobService;

public class GetAllJobsByDateAction extends BasicAction{
	private List<Jobs> jobs;
	public void setJobs(List<Jobs> jobs){
		this.jobs=jobs;
	}
	public List<Jobs> getJobs(){
		return jobs;
	}
	public String execute() throws Exception {
		try {
			JobService jobService = serviceManager.getJobService();
			jobs=jobService.getAllJobsByDate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}
}

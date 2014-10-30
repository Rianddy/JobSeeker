package com.JobSeeker.struts2.action;

import java.util.List;

import com.JobSeeker.model.Jobs;
import com.JobSeeker.service.JobService;

import common.Constants;
import common.GetJobidsFromIndex;
import function.index.indexReader;
public class SearchJobsAction extends BasicAction{
	private String query;
	private String location;
	private List<Jobs> jobs;

	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<Jobs> getJobs() {
		return jobs;
	}
	public void setJobs(List<Jobs> jobs) {
		this.jobs = jobs;
	}
	public String execute() throws Exception {
		try {
			GetJobidsFromIndex getJobids=new GetJobidsFromIndex(query);
			List<String> jobids=getJobids.Search();
			JobService jobService = serviceManager.getJobService();
			jobs=jobService.serachJobsByLocation(jobids,location);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}
}

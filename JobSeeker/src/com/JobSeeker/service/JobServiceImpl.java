package com.JobSeeker.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.JobSeeker.model.*;
import com.JobSeeker.dao.JobDAO;

public class JobServiceImpl implements JobService{
	private JobDAO jobDAO;
	public JobServiceImpl(JobDAO jobDAO){
		this.jobDAO=jobDAO;
	}
	@Override
	public List<Jobs> getCartJobs(Users user) throws Exception{
		List<Jobs> jobs=new ArrayList<Jobs>();
		List<String> jobIds=jobDAO.getCartJobids(user);
		for(int i=0;i<jobIds.size();i++){
			jobs.add(jobDAO.getJobByjobid(jobIds.get(i)));
		}
		return jobs;
	}
	@Override
	public List<Jobs> getAllJobsByDate() throws Exception{
		List<Jobs> jobs=jobDAO.getAllJobsByDate();
		return jobs;
	}
	@Override
	public List<Jobs> serachJobsByLocation(List<String> jobids, String location) throws Exception{
		List<Jobs> jobs;
		if(jobids.size()==0){
			jobs=getAllJobsByDate();
		}else{
			location="%"+location+"%";
			jobs=jobDAO.getJobsByLocationAndPage(jobids, location);
		}
		return jobs;
	}
}

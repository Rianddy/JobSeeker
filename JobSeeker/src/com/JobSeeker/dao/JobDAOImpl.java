package com.JobSeeker.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.JobSeeker.model.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class JobDAOImpl extends DAOSupport implements JobDAO{
	public JobDAOImpl(HibernateTemplate template){
		super(template);
	}

	@Override
	public Jobs getJobByjobid(String jobId){
		String hql="from Jobs where jobid = ?";
		List<Jobs> job=template.find(hql,jobId);
		return job.get(0);
	}
	
	@Override
	public List<Jobs> getAllJobsByDate(){
		String hql="from Jobs order by date DESC";
		List<Jobs> jobs=template.find(hql);
		return jobs;
	}
	@Override
	public List<String> getCartJobids(Users user){
		String hql="select jobid from Connection where username = ? AND connectionstatus= ?";
		List<String> jobIds=template.find(hql, new Object[]{user.getUsername(),"cart"});
		return jobIds;
	}
	@Override
	public List<Jobs> getJobsByLocationAndPage(List<String> jobids, String location){
		String hql="from Jobs where location like (:location) and jobid in (:jobids) order by date DESC";
		List<Jobs> jobIds=template.findByNamedParam(hql,new String[]{ "location", "jobids" },new Object[]{location,jobids});
//		String hql="from Jobs where jobid in (:jobids) order by date DESC";
//		List<Jobs> jobIds=template.findByNamedParam(hql,"jobids",jobids);
		return jobIds;
	}
}

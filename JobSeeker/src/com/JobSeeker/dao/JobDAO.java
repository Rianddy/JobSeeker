package com.JobSeeker.dao;

import java.util.List;

import com.JobSeeker.model.*;
public interface JobDAO {
	List<String> getCartJobids(Users user);
	Jobs getJobByjobid(String jobId);
	List<Jobs> getAllJobsByDate();
	public List<Jobs> getJobsByLocationAndPage(List<String> jobids, String location);
}
package com.JobSeeker.service;
import java.util.List;

import com.JobSeeker.model.*;
public interface JobService {
	public List<Jobs> getCartJobs(Users user) throws Exception;
	public List<Jobs> getAllJobsByDate() throws Exception;
	public List<Jobs> serachJobsByLocation(List<String> jobids, String location) throws Exception;
}

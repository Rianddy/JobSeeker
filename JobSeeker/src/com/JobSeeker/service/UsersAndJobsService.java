package com.JobSeeker.service;

import java.util.List;

import com.JobSeeker.model.Connection;

public interface UsersAndJobsService {
	public boolean saveJobToCart(Connection usersAndJobs) throws Exception;
	public void deleteJobInCart(Connection usersAndJobs) throws Exception;
	public Connection getUsersAndJobsByUsernameAndJobid(Connection usersAndJobs) throws Exception;
	public boolean applyJobsInCart(List<Connection> usersAndJobsList) throws Exception;
}

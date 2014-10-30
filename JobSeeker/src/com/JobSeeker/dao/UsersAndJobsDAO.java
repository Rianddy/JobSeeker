package com.JobSeeker.dao;

import java.util.List;

import com.JobSeeker.model.Connection;

public interface UsersAndJobsDAO {
	
	void saveToCart(Connection usersAndJobs);
	boolean existJobInCart(Connection usersAndJobs);
	void deleteJobInCart(Connection usersAndJobs);
	Connection getUsersAndJobsByUsernameAndJobid(Connection usersAndJobs);
	public boolean applyJobsInCart(List<Connection> usersAndJobsList);
}

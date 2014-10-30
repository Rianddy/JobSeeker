package com.JobSeeker.service;

import java.util.ArrayList;
import java.util.List;

import com.JobSeeker.model.*;
import com.JobSeeker.dao.UsersAndJobsDAO;
import common.Constants;
public class UsersAndJobsServiceImpl implements UsersAndJobsService{
	private UsersAndJobsDAO usersAndJobsDAO;
	public UsersAndJobsServiceImpl(UsersAndJobsDAO usersAndJobsDAO){
		this.usersAndJobsDAO=usersAndJobsDAO;
	}
	@Override
	public boolean saveJobToCart(Connection usersAndJobs){
		if(!usersAndJobsDAO.existJobInCart(usersAndJobs)){
			usersAndJobsDAO.saveToCart(usersAndJobs);
			return true;
		}
		else
			return false;
	}
	@Override
	public void deleteJobInCart(Connection usersAndJobs){
		usersAndJobsDAO.deleteJobInCart(getUsersAndJobsByUsernameAndJobid(usersAndJobs));
	}
	@Override
	public Connection getUsersAndJobsByUsernameAndJobid(Connection usersAndJobs){
		return usersAndJobsDAO.getUsersAndJobsByUsernameAndJobid(usersAndJobs);
	}
	@Override
	public boolean applyJobsInCart(List<Connection> usersAndJobsList){
		List<Connection> completeUsersAndJobsList=new ArrayList<Connection>();
		for(int i=0;i<usersAndJobsList.size();i++){
			Connection currentConnection=usersAndJobsDAO.getUsersAndJobsByUsernameAndJobid(usersAndJobsList.get(i));
			currentConnection.setConnectionstatus(Constants.APPLIED);
			completeUsersAndJobsList.add(currentConnection);
		}
		return usersAndJobsDAO.applyJobsInCart(completeUsersAndJobsList);
	}
}

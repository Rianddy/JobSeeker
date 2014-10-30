package com.JobSeeker.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.JobSeeker.model.Connection;
import com.JobSeeker.model.Users;

public class UsersAndJobsDAOImpl extends DAOSupport implements UsersAndJobsDAO{
	public UsersAndJobsDAOImpl(HibernateTemplate template){
		super(template);
	}
	@Override
	public boolean existJobInCart(Connection usersAndJobs){
		String hql="from Connection where username = ? and jobid = ?";
		List<Connection> jobs=template.find(hql, new Object[]{usersAndJobs.getUsername(),usersAndJobs.getJobid()});
		if(jobs.size()>0)
			return true;
		else
			return false;
	}	
	@Override
	public void saveToCart(Connection usersAndJobs){
		String uId=UUID.randomUUID().toString();
		usersAndJobs.setConnectionid(uId);
		usersAndJobs.setConnectionstatus("cart");
		template.save(usersAndJobs);
	}
	@Override
	public void deleteJobInCart(Connection usersAndJobs){
		template.delete(usersAndJobs);
	}
	@Override
	public Connection getUsersAndJobsByUsernameAndJobid(Connection usersAndJobs){
		String hql="from Connection where username = ? and jobid = ?";
		List<Connection> jobs=template.find(hql, new Object[]{usersAndJobs.getUsername(),usersAndJobs.getJobid()});
		return jobs.get(0);
	}
	@Override
	public boolean applyJobsInCart(List<Connection> usersAndJobsList){
		template.saveOrUpdateAll(usersAndJobsList);
		return true;
	}
}

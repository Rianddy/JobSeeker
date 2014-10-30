package com.JobSeeker.dao;

import com.JobSeeker.model.Users;
public interface UserDAO {
	void save(Users user);
	String getPasswordMD5(Users user);
	boolean exists(Users user);
	void delete(Users user);
}

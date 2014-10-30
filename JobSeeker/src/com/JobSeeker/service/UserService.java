package com.JobSeeker.service;
import com.JobSeeker.model.Users;
public interface UserService {
	public void addUser(Users user) throws Exception;
	public boolean verifyUser(Users user) throws Exception;
	public void addCookieUser(Users user) throws Exception;
	public void deleteCookieUser(Users user) throws Exception;
}

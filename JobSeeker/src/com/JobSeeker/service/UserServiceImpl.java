package com.JobSeeker.service;

import com.JobSeeker.model.Users;
import com.JobSeeker.dao.UserDAO;

public class UserServiceImpl implements UserService{
	private UserDAO userDAO;
	public UserServiceImpl(UserDAO userDAO){
		this.userDAO=userDAO;
	}
	@Override
	public void addUser(Users user) throws Exception{
		if(userDAO.exists(user)){
			throw new Exception ("User with this username already exists!");
		}
		else
			userDAO.save(user);
	}
	@Override
	public boolean verifyUser(Users user){
		String passwordMD5=userDAO.getPasswordMD5(user);
		boolean result=false;
		try{
			result=user.getPasswordMD5().equals(passwordMD5)?true:false;
		}catch(Exception e){
		}
		return result;
	}
	@Override
	public void addCookieUser(Users user){
		userDAO.save(user);
	}
	@Override
	public void deleteCookieUser(Users user){
		userDAO.delete(user);
	}
}

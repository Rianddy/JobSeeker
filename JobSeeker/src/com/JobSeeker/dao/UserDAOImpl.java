package com.JobSeeker.dao;

import org.springframework.orm.hibernate3.HibernateTemplate;
import com.JobSeeker.model.Users;
import java.util.List;
public class UserDAOImpl extends DAOSupport implements UserDAO{
	public UserDAOImpl(HibernateTemplate template){
		super(template);
	}
	@Override
	public void save(Users user){
		template.save(user);
	}
	@Override
	public String getPasswordMD5(Users user){
		String hql="SELECT passwordMD5 FROM Users WHERE username = ? AND official = 'True'";
		List<String> passwordMD5=template.find(hql, user.getUsername());
		if(passwordMD5.size()>0)
			return passwordMD5.get(0);
		else
			return null;
	}
	@Override
	public boolean exists(Users user){
		return (getPasswordMD5(user)!=null)?true:false;
	}
	@Override
	public void delete(Users user){
		template.delete(user); 
	}
}

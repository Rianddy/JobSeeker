package com.JobSeeker.dao;

import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.JobSeeker.model.Feeds;
import com.JobSeeker.model.Jobs;
import com.JobSeeker.model.Users;

public class FeedDAOImpl extends DAOSupport implements FeedDAO{
	public FeedDAOImpl(HibernateTemplate template){
		super(template);
	}
	@Override
	public List<Feeds> getFeeds(Users user){
		String hql="from Feeds where username = ?";
		List<Feeds> feeds=template.find(hql,user.getUsername());
		return feeds;
	}
	
	@Override
	public void save(Feeds feed){
		template.save(feed);
	}
	
	@Override
	public void deleteFeed(Feeds feed){
		template.delete(feed);
	}
	@Override
	public Feeds getFeed(Feeds feed){
		String hql="from Feeds where feedid = ?";
		List<Feeds> feeds=template.find(hql,feed.getFeedid());
		if(feeds.size()>0)
			return feeds.get(0);
		else
			return null;
	}
}

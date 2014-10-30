package com.JobSeeker.service;

import java.util.List;

import com.JobSeeker.dao.FeedDAO;
import com.JobSeeker.model.Feeds;
import com.JobSeeker.model.Users;

public class FeedServiceImpl  implements FeedService{
	private FeedDAO feedDAO;
	public FeedServiceImpl(FeedDAO feedDAO){
		this.feedDAO=feedDAO;
	}
	@Override
	public List<Feeds> getFeeds(Users user) throws Exception{
		List<Feeds> feeds=feedDAO.getFeeds(user);
		return feeds;
	}
	@Override
	public void save(Feeds feed) throws Exception{
		feedDAO.save(feed);
	}
	@Override
	public void deleteFeed(Feeds feed) throws Exception{
		if (getFeed(feed)!=null)
			feedDAO.deleteFeed(feed);
	}
	@Override
	public Feeds getFeed(Feeds feed) throws Exception{
		Feeds completeFeed=feedDAO.getFeed(feed);
		return completeFeed;
	}
}

package com.JobSeeker.struts2.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.JobSeeker.model.*;
import com.JobSeeker.service.FeedService;

import common.Constants;
import common.CookieUtil;

public class GetFeedsAction extends BasicAction{
	List<Feeds> feeds;
	Users user=new Users();
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public List<Feeds> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<Feeds> feeds) {
		this.feeds = feeds;
	}

	@Override
	public String execute() throws Exception{
		try {
			FeedService feedService = serviceManager.getFeedService();
			HttpSession session = request.getSession();
			String username=(String)session.getAttribute(Constants.SESSION_USER);
			user.setUsername(username);
			feeds=feedService.getFeeds(user);
			return SUCCESS;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ERROR;
		}
		
	}
}

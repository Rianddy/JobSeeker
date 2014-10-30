package com.JobSeeker.struts2.action;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.JobSeeker.model.Feeds;
import com.JobSeeker.service.FeedService;

import common.Constants;
import common.CookieUtil;

public class CreateFeedAction extends BasicAction{
	String createlocation;
	String createtitle;
	public String getCreatelocation() {
		return createlocation;
	}
	public void setCreatelocation(String createlocation) {
		this.createlocation = createlocation;
	}
	public String getCreatetitle() {
		return createtitle;
	}
	public void setCreatetitle(String createtitle) {
		this.createtitle = createtitle;
	}
	public String execute() throws Exception {
		try {
			FeedService feedService = serviceManager.getFeedService();
			Feeds feed=new Feeds();
			HttpSession session = request.getSession();
			String username=(String)session.getAttribute(Constants.SESSION_USER);
			feed.setUsername(username);
			String uId=UUID.randomUUID().toString();
			feed.setFeedid(uId);
			java.util.Date dt = new java.util.Date();
			java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = sdf.format(dt);
			feed.setDate(currentTime);
			feed.setLocation(createlocation);
			feed.setTitle(createtitle);
			feedService.save(feed);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}
}

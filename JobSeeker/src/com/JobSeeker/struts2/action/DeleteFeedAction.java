package com.JobSeeker.struts2.action;

import javax.servlet.http.HttpSession;

import com.JobSeeker.model.Feeds;
import com.JobSeeker.service.FeedService;

import common.Constants;
import common.CookieUtil;

public class DeleteFeedAction extends BasicAction{
	String feedid;
	public String getFeedid() {
		return feedid;
	}
	public void setFeedid(String feedid) {
		this.feedid = feedid;
	}
	public String execute() throws Exception {
		try {
			FeedService feedService = serviceManager.getFeedService();
			Feeds feed=new Feeds();
			HttpSession session = request.getSession();
			String username=(String)session.getAttribute(Constants.SESSION_USER);
			feed.setUsername(username);
			feed.setFeedid(feedid);
			feedService.deleteFeed(feed);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return SUCCESS;
	}
}

package com.JobSeeker.service;

public class ServiceManager {
	private UserService userService;
	private JobService jobService;
	private UsersAndJobsService usersAndJobsService;
	private FeedService feedService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public JobService getJobService() {
		return jobService;
	}
	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}
	public UsersAndJobsService getUsersAndJobsService() {
		return usersAndJobsService;
	}
	public void setUsersAndJobsService(UsersAndJobsService usersAndJobsService) {
		this.usersAndJobsService = usersAndJobsService;
	}
	public FeedService getFeedService() {
		return feedService;
	}
	public void setFeedService(FeedService feedService) {
		this.feedService = feedService;
	}
}

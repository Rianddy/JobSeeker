package com.JobSeeker.service;

import java.util.List;

import com.JobSeeker.model.Feeds;
import com.JobSeeker.model.Users;

public interface FeedService {
	void save(Feeds feed) throws Exception;
	List<Feeds> getFeeds(Users user) throws Exception;
	void deleteFeed(Feeds feed) throws Exception;
	Feeds getFeed(Feeds feed) throws Exception;
}

package com.JobSeeker.dao;


import java.util.List;

import com.JobSeeker.model.*;
public interface FeedDAO {
	void save(Feeds feed);
	List<Feeds> getFeeds(Users user);
	void deleteFeed(Feeds feed);
	Feeds getFeed(Feeds feed);
}
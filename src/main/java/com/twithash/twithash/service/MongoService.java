package com.twithash.twithash.service;

import com.twithash.twithash.model.Tweet;

import reactor.core.publisher.Flux;


public interface MongoService {

	public Flux<Tweet> findByTag(String tag);
	public void saveTweet( Tweet tweet);
}

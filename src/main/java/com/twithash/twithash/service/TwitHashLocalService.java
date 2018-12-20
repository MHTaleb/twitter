package com.twithash.twithash.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.twithash.twithash.model.Tweet;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class TwitHashLocalService {
	
	private final TwitHashRemoteService hashRemoteService;
	private final MongoService mongoService;
	
	public Flux<Tweet> findByTag(String tag) {
		return mongoService.findByTag(tag);
	}

	public void save(Tweet tweet) {
		mongoService.saveTweet(tweet);
	}

}

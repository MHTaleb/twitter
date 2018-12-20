package com.twithash.twithash.service;

import org.springframework.stereotype.Service;

import com.twithash.twithash.model.Tweet;
import com.twithash.twithash.repository.TweetMongoRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

/**
 * a simple mongo service implementation
 * */

@Service
@AllArgsConstructor
public class MongoServiceImp implements MongoService  {

	private final TweetMongoRepository tweetMongoRepo; 
	
        @Override
	public Flux<Tweet> findByTag(String tag) {
		return tweetMongoRepo.findAllByTag(tag).doOnEach(System.out::println);
	}

        @Override
	public void saveTweet( Tweet tweet) {
		tweetMongoRepo.save(tweet).subscribe();
	}

}

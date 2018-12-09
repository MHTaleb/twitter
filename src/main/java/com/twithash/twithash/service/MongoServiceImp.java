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

	private TweetMongoRepository tweetMongoRepo; 
	
	public Flux<Tweet> findByTag(String tag) {
		// TODO Auto-generated method stub
		return tweetMongoRepo.findAllByTag(tag).doOnEach(System.out::println);
	}

	public void saveTweet( Tweet tweet) {
		// TODO Auto-generated method stub
		System.out.println("saving tweet "+tweet);
		tweetMongoRepo.save(tweet).subscribe();
	}

}

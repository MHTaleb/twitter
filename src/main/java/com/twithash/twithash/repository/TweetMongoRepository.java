package com.twithash.twithash.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.twithash.twithash.model.Tweet;

import reactor.core.publisher.Flux;
/**
 * simple mongo reactive repo
 * reading by tag returns a flux 
 * */
public interface TweetMongoRepository extends ReactiveCrudRepository<Tweet, String>{
	public Flux<Tweet> findAllByTag(String tag);
}

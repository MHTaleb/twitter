package com.twithash.twithash.service;

import org.springframework.stereotype.Service;

import com.twithash.twithash.model.Tweet;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;

@Service
@AllArgsConstructor
public class TwitHashRemoteService {

	private final Configuration configuration;
	
	public Flux<Tweet> findByTag(String hashtag) throws TwitterException,Exception {
		return Flux.create(sink -> {
			TwitterStream twitterStream = new TwitterStreamFactory(configuration).getInstance();
			twitterStream.onStatus(status -> sink.next(Tweet.fromStatus(status,hashtag)));
			twitterStream.onException(sink::error);
			twitterStream.filter(hashtag);
			sink.onCancel(twitterStream::shutdown);
		});	

	}

}

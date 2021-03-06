package com.twithash.twithash.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.twithash.twithash.model.Tweet;
import com.twithash.twithash.service.TwitHashLocalService;
import com.twithash.twithash.service.TwitHashRemoteService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

/**
 * rest reactive controller because I m using webflux
 * 
 * AllArgsConstructor from lombok helps me to avoid writing autowired everywhere
 *   see more about spring injection by constructor
 * */

@RestController
@AllArgsConstructor
public class SearchController {

	private final TwitHashLocalService localService;
	private final TwitHashRemoteService remoteService;
	
	@GetMapping(value="search/{tag}",produces=MediaType.TEXT_EVENT_STREAM_VALUE) 
	public Flux<Tweet> getByTag(@PathVariable String tag) throws Exception{
		tag = "#" + tag;
		return localService
                        .findByTag(tag)
                        .mergeWith(
                                remoteService
                                        .findByTag(tag)
                                        .doOnNext(tweet -> localService.save(tweet))
                        )
                        .distinct();
	}
}

package com.twithash.twithash.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import twitter4j.Status;

/**
 * for simplifying purpose 
 * 
 * this document is a mongo collection element
 * 
 * tweetdata is the id: we can have multiple tag value where the content is the same but the user is diffirent
 * so this model can be updated to take care of profile picture and more data
 * I keeped it simple 
 * 
 * */

@Document(collection="tweet")
@AllArgsConstructor
@Data
public class Tweet {
	

    @JsonProperty("tag")
	private  String tag;
	
    @Id
    @JsonProperty("tweetData")
	private  TweetData tweetData;

    

	public static Tweet fromStatus(Status status,String tag) {
		String text = status.getText();
		String name = status.getUser().getName();
		return new Tweet(tag,new TweetData(text, name));
	}

	
	
}

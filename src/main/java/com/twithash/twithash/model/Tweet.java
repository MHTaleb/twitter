package com.twithash.twithash.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import twitter4j.Status;

/**
 * for simplifying purpose
 * 
 * this document is a mongo collection element
 * 
 * tweetdata is the id: we can have multiple tag value where the content is the
 * same but the user is diffirent so this model can be updated to take care of
 * profile picture and more data I keeped it simple
 * 
 */

@Document(collection = "tweet")
@AllArgsConstructor
@Data
public class Tweet {

	private String tag;

	@Id
	private TweetID tweetID;

	private String userImage;

	private String country;

	private String placeFullName;

        /**
         * converting a status tweeter api response to a Tweet pojo object (entity)
         */
	public static Tweet fromStatus(Status status, String tag) {
		String text = status.getText();
		String name = status.getUser().getName();
		String image_path = status.getUser().getOriginalProfileImageURL();
		String country = status.getPlace() == null ? "" : status.getPlace().getCountryCode();
		String placeFullName = status.getUser().getLocation() == null ?"" : status.getUser().getLocation() ;
		return new Tweet(tag, new TweetID(text, name), image_path, country, placeFullName);
	}

}

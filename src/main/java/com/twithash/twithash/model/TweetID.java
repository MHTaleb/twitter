package com.twithash.twithash.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TweetID {

    @JsonProperty("text")
	private  String text;

	
    @JsonProperty("name")
	private  String name;
	
}

package com.twithash.twithash.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * fetching twitter config keys and token from application.properties
 * where the keys are prefixed by twithash
 * */

@ConfigurationProperties(prefix = "twithash")
@Data
public class TwitterAPIConfigurationProperties {
	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;
}

package com.twithash.twithash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.twithash.twithash.config.TwitterAPIConfigurationProperties;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

@EnableReactiveMongoRepositories(basePackages = "com.twithash.twithash.repository")
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableConfigurationProperties(TwitterAPIConfigurationProperties.class)
public class TwithashApplication extends AbstractReactiveMongoConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(TwithashApplication.class, args);
    }


    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return "twithash";
    }

    /**
     * oauth1 config for twitter
     *
     * @param properties spring properties config object
     * @return tweeter configuration
     */
    @Bean
    public Configuration configuration(TwitterAPIConfigurationProperties properties) {
        return new ConfigurationBuilder()
                .setOAuthConsumerKey(properties.getConsumerKey())
                .setOAuthConsumerSecret(properties.getConsumerSecret())
                .setOAuthAccessToken(properties.getAccessToken())
                .setOAuthAccessTokenSecret(properties.getAccessTokenSecret())
                .build();
    }

}

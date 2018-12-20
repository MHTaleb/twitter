package com.twithash.twithash;

import com.twithash.twithash.service.TwitHashLocalService;


import com.twithash.twithash.model.Tweet;
import com.twithash.twithash.model.TweetID;
import com.twithash.twithash.service.TwitHashRemoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest
public class TwitHashControllerTests {

    @Autowired
    private WebTestClient client;

    @MockBean
    private TwitHashRemoteService remoteService;

    @MockBean
    private TwitHashLocalService localService;

    @Test
    public void t1_getTweets() throws Exception{

        Flux<Tweet> remoteTweets = Flux.just(
                new Tweet("tag1",new TweetID("text","name"),"userimage","country","place"),
                new Tweet("tag2",new TweetID("text","name"),"userimage","country","place")
        );
        when(remoteService.findByTag(any())).thenReturn( remoteTweets);

        Flux<Tweet> localTweets = Flux.just(
                new Tweet("tag1",new TweetID("text","name"),"userimage","country","place")
        );
        when(localService.findByTag(any())).thenReturn( localTweets);

        Flux<Tweet> responseBody = client.get()
                .uri("search/tag")
                .exchange()
                .returnResult(Tweet.class)
                .getResponseBody();
        StepVerifier.create(responseBody)
        .expectNextCount(1)
        .expectNextCount(2)
                .expectComplete();
        responseBody = client.get()
                .uri("search/tag")
                .exchange()
                .returnResult(Tweet.class)
                .getResponseBody();
        StepVerifier.create(responseBody)
                .expectNext(new Tweet("tag1",new TweetID("text","name"),"userimage","country","place"))
                .expectNext(new Tweet("tag2",new TweetID("text","name"),"userimage","country","place"))
                .verifyComplete();

    }
}

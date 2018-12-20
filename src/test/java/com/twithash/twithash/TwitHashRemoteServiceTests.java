package com.twithash.twithash;

/**
 * Created by win 10 on 18/12/2018.
 */

import com.twithash.twithash.model.Tweet;
import com.twithash.twithash.model.TweetID;
import com.twithash.twithash.service.TwitHashLocalService;
import com.twithash.twithash.service.TwitHashRemoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest
public class TwitHashRemoteServiceTests {



    @MockBean
    private TwitHashLocalService localService;
    @MockBean
    private TwitHashRemoteService remoteService;

    @Test
    public void t1_findByTag_reactor_test() throws Exception {
        Flux<Tweet> tags = Flux.just(
                new Tweet("tag1",new TweetID("text","name"),"userimage","country","place"),
                new Tweet("tag2",new TweetID("text","name"),"userimage","country","place")
        );
        when(remoteService.findByTag(any())).thenReturn( tags);

        StepVerifier.create(remoteService.findByTag("testAny"))
                .expectNext(new Tweet("tag1",new TweetID("text","name"),"userimage","country","place"))
                .expectNext(new Tweet("tag2",new TweetID("text","name"),"userimage","country","place"))
                .verifyComplete();
    }

}

package fr.univaix.iut.pokebattle.twitter;

import java.util.Date;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class TweetTest {

    @Test
    public void testCreateTweetNoOwnerNoDate() {
        Tweet tweet = new Tweet("coucou !");
        assertEquals(null, tweet.getScreenName());
        assertEquals("coucou !", tweet.getText());
    }

    @Test
    public void testCreateTweetOwnerNoDate() {
        Tweet tweet = new Tweet("nedseb", "coucou !");
        assertEquals("nedseb", tweet.getScreenName());
        assertEquals("coucou !", tweet.getText());
    }

    @Test
    public void testCreateTweetOwnerDate() {
        Tweet tweet = new Tweet("TwitterTest", "Coucou!", new Date());
        assertThat(tweet.getCreatedAt()).isNotNull();
    }

}

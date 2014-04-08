package fr.univaix.iut.pokebattle.twitter;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.run.PokemonMain;
import fr.univaix.iut.pokebattle.tuse.Credentials;

public class TwitterUserStreamEasyBuilderTest {
    Twitter     twitter     = null;
    Credentials credentials = null;
    TwitterFactory factory = null;

    @Before
    public void setUp() throws IOException  {
        InputStream inputStream = PokemonMain.class.getClassLoader()
                .getResourceAsStream("testCredentials.properties");
        credentials = Credentials.loadCredentials(inputStream);
        factory = new TwitterFactory();
        twitter = factory.getInstance();
    }
    
    @Test
    public void test() throws IOException {
      
        
        TwitterUserStreamEasyBuilder tuseb = new TwitterUserStreamEasyBuilder(
                credentials, twitter, new JudgeBot());
        assertThat(tuseb).isNotNull();
    }
   @Test
   public void test_process() throws IOException, TwitterException{
        JudgeBot juge = new JudgeBot();
        TwitterUserStreamEasyBuilder tuseb = new TwitterUserStreamEasyBuilder(
                credentials, twitter, juge);
        Status status = twitter.showStatus(twitter.getId());
        tuseb.processNewQuestion(status, juge);
        assertThat(tuseb.isNotANewQuestion(status)).isTrue();
    }
   
}

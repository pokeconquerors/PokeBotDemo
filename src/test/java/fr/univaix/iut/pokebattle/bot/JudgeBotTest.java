package fr.univaix.iut.pokebattle.bot;

import java.util.Date;

import fr.univaix.iut.pokebattle.twitter.Tweet;

import org.junit.BeforeClass;
import org.junit.Test;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class JudgeBotTest {
    JudgeBot judgeBot = new JudgeBot();
	private static JudgeBot judge = null;
	
	@BeforeClass
	public static void setUp () {
		TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
		judge = new JudgeBot();
		judge.setTwitter(twitter);
	}
    @Test
    public void testAsk() throws Exception {
        assertThat(judgeBot.ask(new Tweet("@PokeConquerors Salut"))).isNotNull();
    }
    
    @Test
    public void testNotImplementedNoAnswer() throws Exception {
        assertThat(judgeBot.ask(new Tweet("@PokeConquerors This is not a question."))).isNotNull();
    }
    
    @Test
    public void testNoOwnerNoSalut() {
    	assertThat(judgeBot.ask(new Tweet("@PokeConquerors Salut!"))).isNotNull();
    }
    
    @Test
    public void testTweetHadOwnerHaveAnswer_Salut() {
    	assertThat(judgeBot.ask(new Tweet("TwitterTest","@PokeConquerors Salut!"))).isNotNull();
    }

    @Test
    public void testTweetHadOwnerHaveAnswer_Gym() {

    	assertThat(judge.ask(new Tweet("TwitterTest","@PokeConquerors Gym?"))).isNotNull();
    }
    
    @Test
    public void testNoOwnerHaveAnswer_Gym() {
    	assertThat(judgeBot.ask(new Tweet("@PokeConquerors Gym?"))).isNotNull();
    }
    
    @Test
    public void testMultiple_Salut_Gym() {
    	judge.setArene(null);
    	assertEquals("@TwitterTest no Gym", judge.ask(new Tweet("TwitterTest","@PokeConquerors Salut! Gym?")));
    }
    
    @Test
    public void testMultiple_Gym_Salut() {
    	assertEquals("@TwitterTest Salisalut très cher voisin !", judgeBot.ask(new Tweet("TwitterTest","@Gym? Salut!")));
    }
    @Test
    public void testisoneHourBetween(){
    	assertThat(judge.isMoreThanAnHour(new Date(3600002), new Date(1))).isTrue();
    }
}

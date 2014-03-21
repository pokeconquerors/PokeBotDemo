package fr.univaix.iut.pokebattle.bot;

import fr.univaix.iut.pokebattle.twitter.Tweet;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class JudgeBotTest {
    JudgeBot judgeBot = new JudgeBot();

    @Test
    public void testAsk() throws Exception {
        assertThat(judgeBot.ask(new Tweet("Salut"))).isNull();
    }
    
    @Test
    public void testNotImplementedNoAnswer() throws Exception {
        assertThat(judgeBot.ask(new Tweet("This is not a question."))).isNull();
    }
    
    @Test
    public void testNoOwnerNoSalut() {
    	assertThat(judgeBot.ask(new Tweet("Salut!"))).isNull();
    }
    
    @Test
    public void testTweetHadOwnerHaveAnswer_Salut() {
    	assertThat(judgeBot.ask(new Tweet("TwitterTest","Salut!"))).isNotNull();
    }

   
}

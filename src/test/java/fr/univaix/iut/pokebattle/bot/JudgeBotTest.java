package fr.univaix.iut.pokebattle.bot;

import fr.univaix.iut.pokebattle.twitter.Tweet;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class JudgeBotTest {
    JudgeBot judgeBot = new JudgeBot();

    @Test
    public void testAsk() throws Exception {
        assertThat(judgeBot.ask(new Tweet("@PokeConquerors Salut"))).isNull();
    }
    
    @Test
    public void testNotImplementedNoAnswer() throws Exception {
        assertThat(judgeBot.ask(new Tweet("@PokeConquerors This is not a question."))).isNull();
    }
    
    @Test
    public void testNoOwnerNoSalut() {
    	assertThat(judgeBot.ask(new Tweet("@PokeConquerors Salut!"))).isNull();
    }
    
    @Test
    public void testTweetHadOwnerHaveAnswer_Salut() {
    	assertThat(judgeBot.ask(new Tweet("TwitterTest","@PokeConquerors Salut!"))).isNotNull();
    }

    @Test
    public void testTweetHadOwnerHaveAnswer_Gym() {
    	assertThat(judgeBot.ask(new Tweet("TwitterTest","@PokeConquerors Gym?"))).isNotNull();
    }
    
    @Test
    public void testNoOwnerHaveAnswer_Gym() {
    	assertThat(judgeBot.ask(new Tweet("@PokeConquerors Gym?"))).isNull();
    }
    
    @Test
    public void testMultiple_Salut_Gym() {
    	assertEquals("@TwitterTest no Gym", judgeBot.ask(new Tweet("TwitterTest","@PokeConquerors Salut! Gym?")));
    }
    
    @Test
    public void testMultiple_Gym_Salut() {
    	assertEquals("@TwitterTest Salisalut très cher voisin !", judgeBot.ask(new Tweet("TwitterTest","@Gym? Salut!")));
    }   
}

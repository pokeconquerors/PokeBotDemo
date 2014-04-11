package fr.univaix.iut.pokebattle.smartcell;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersGymCellTest {

    @Test
    public void Test_JudgeAnswerArene_Viridian() {
        JudgeAnswersGymCell cell = new JudgeAnswersGymCell(new JudgeBot(
                "ViridianGym"));
        assertEquals("@TwitterTest my Gym is ViridianGym but maybe ...",
                cell.ask(new Tweet("TwitterTest", "@PokeConquerors Gym?")));
    }

    @Test
    public void Test_JudgeAnswerArene_No() {
        TwitterFactory factory = new TwitterFactory();
        Twitter twitter = factory.getInstance();
        JudgeBot juge = new JudgeBot();
        juge.setTwitter(twitter);
        JudgeAnswersGymCell cell = new JudgeAnswersGymCell(juge);
        assertEquals("@TwitterTest no Gym",
                cell.ask(new Tweet("TwitterTest", "@PokeConquerors Gym?")));

    }

    @Test
    public void Test_JudgeAnswerArene_Null() {
        JudgeAnswersGymCell cell = new JudgeAnswersGymCell(new JudgeBot());
        assertThat(cell.ask(new Tweet("TwitterTest", "@Gym?"))).isNull();
    }

    @Test
    public void Test_JudgeAnswerArene_regex() {
        JudgeAnswersGymCell cell = new JudgeAnswersGymCell(new JudgeBot(
                "TestGym"));
        assertThat(cell.ask(new Tweet("TwitterTest", " Gym ?"))).isNotNull();
    }

    @Test
    public void test_Regex_Wrong() {
        JudgeAnswersGymCell cell = new JudgeAnswersGymCell(new JudgeBot(
                "TestGym"));
        assertThat(cell.ask(new Tweet("Boulet", "@PokeConquerors Gyma?")))
                .isNull();
    }

    @Test
    public void Test_JudgeAnswerArene_Exception() {
        JudgeBot juge = new JudgeBot();
        JudgeAnswersGymCell cell = new JudgeAnswersGymCell(juge);
        assertEquals("@TwitterTest no Gym",
                cell.ask(new Tweet("TwitterTest", "@PokeConquerors Gym?")));

    }
    
    @Test
    public void test_KeyWord() {
        JudgeAnswersGymCell cell = new JudgeAnswersGymCell(new JudgeBot());
        assertEquals(".*\\s+(?i)gym\\s*\\?.*", cell.getKeyWord());
    }
}

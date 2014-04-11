package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersHireCellTest {
    private static JudgeBot judge = null;
    private static String arene   ="no Gym";

    @BeforeClass
    public static void setUp() {
        TwitterFactory factory = new TwitterFactory();
        Twitter twitter = factory.getInstance();
        judge = new JudgeBot();
        judge.setTwitter(twitter);
    }

    @Test
    public void Test_Hire_possible() {
        JudgeAnswersHireCell cell = new JudgeAnswersHireCell(judge);
        judge.setArene(null);
        assertEquals("@titi my gym is @titi",
                cell.ask(new Tweet("titi", " Hire!")));
    }

    @Test
    public void Test_Hire_impossible() {
        JudgeAnswersHireCell cell = new JudgeAnswersHireCell(judge);
        cell.ask(new Tweet("toto", " Hire!"));
        assertEquals("@titi @toto is my owner but maybe ...",
                cell.ask(new Tweet("titi", " Hire!")));
    }

    @Test
    public void test_Hire_ExceptionUpdate() {
        JudgeAnswersHireCell cell = new JudgeAnswersHireCell(new JudgeBot());
        cell.ask(new Tweet("toto", " Hire!"));
    }
    
    @Test
    public void test_KeyWord() {
        JudgeAnswersHireCell cell = new JudgeAnswersHireCell(new JudgeBot());
        assertEquals(".*\\s+(?i)hire\\s*!.*", cell.getKeyWord());
    }
    @AfterClass
    public static void reset_gym() throws TwitterException {
        TwitterFactory factory = new TwitterFactory();
        Twitter twitter = factory.getInstance();
        judge = new JudgeBot();
        judge.setTwitter(twitter);
        judge.setArene(arene);
        judge.update();
    }

}

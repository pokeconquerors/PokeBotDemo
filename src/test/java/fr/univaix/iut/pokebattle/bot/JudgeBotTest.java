package fr.univaix.iut.pokebattle.bot;

import java.util.Date;

import fr.univaix.iut.pokebattle.twitter.Tweet;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class JudgeBotTest {
    JudgeBot                judgeBot = new JudgeBot();
    private static JudgeBot judge    = null;

    @BeforeClass
    public static void setUp() {
        TwitterFactory factory = new TwitterFactory();
        Twitter twitter = factory.getInstance();
        judge = new JudgeBot();
        judge.setScreenName("PokeConquerors");
        judge.setTwitter(twitter);
    }

    @Before
    public void setUp_2() {
        judgeBot.setScreenName("PokeConquerors");
    }

    @Test
    public void testAsk() throws Exception {
        assertThat(judgeBot.ask(new Tweet("@PokeConquerors Salut"))).isNotNull();
    }

    @Test
    public void testNotImplementedNoAnswer() throws Exception {
        assertThat(
                judgeBot.ask(new Tweet(
                        "@PokeConquerors This is not a question.")))
                .isNotNull();
    }

    @Test
    public void testNoOwnerNoSalut() {
        assertThat(judgeBot.ask(new Tweet("@PokeConquerors Salut!")))
                .isNotNull();
    }

    @Test
    public void testTweetHadOwnerHaveAnswer_Salut() {
        assertThat(
                judgeBot.ask(new Tweet("TwitterTest", "@PokeConquerors Salut!")))
                .isNotNull();
    }

    @Test
    public void testTweetHadOwnerHaveAnswer_Gym() {

        assertThat(judge.ask(new Tweet("TwitterTest", "@PokeConquerors Gym?")))
                .isNotNull();
    }

    @Test
    public void testNoOwnerHaveAnswer_Gym() {
        assertThat(judgeBot.ask(new Tweet("@PokeConquerors Gym?"))).isNotNull();
    }

    @Test
    public void testMultiple_Salut_Gym() {
        judge.setArene(null);
        assertEquals("@TwitterTest no Gym", judge.ask(new Tweet("TwitterTest",
                "@PokeConquerors Salut! Gym?")));
    }

    @Test
    public void testMultiple_Gym_Salut() {
        assertEquals("@TwitterTest Salisalut très cher voisin !",
                judgeBot.ask(new Tweet("TwitterTest", "@Gym? Salut!")));
    }

    @Test
    public void testisoneHourBetween() {
        assertThat(judge.isMoreThanAnHour(new Date(3600002), new Date(1)))
                .isTrue();
    }

    @Test
    public void test_getId() {
        JudgeBot judge = new JudgeBot();
        judge.setId(0);
        assertEquals(0, judge.getId());
    }

    @Test
    public void test_getTwitter() {
        assertThat(judge.getTwitter()).isNotNull();
    }

    @Test
    public void test_ElementInList() {
        JudgeBot judge2 = new JudgeBot();
        judge2.pushPokemon("bulbizarbi", "nebsed", null, null, true);
        judge2.pushPokemon("bulbizarre", "nedseb", null, null, true);
        assertEquals(null, judge2.getElementInList("BulbiVraimentZarbi", 0, 0));
    }
}

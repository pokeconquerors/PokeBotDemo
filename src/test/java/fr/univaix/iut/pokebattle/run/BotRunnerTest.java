package fr.univaix.iut.pokebattle.run;

import static org.fest.assertions.Assertions.assertThat;
import java.io.InputStream;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;

public class BotRunnerTest {

    @Test
    public void test_Constructeur() {
        InputStream is = BotRunner
                .getResourceAsStream("testCredentials.properties");
        assertThat(is).isNotNull();
    }

    @Test
    public void test_runBot_error() {
        try {
            BotRunner
                    .runBot(new JudgeBot(), "testCredentialsErreur.properties");
        } catch (Exception e) {
            assertThat(e).isNotNull();
        }
    }

    @Test
    public void test_runBot_noerror() {
        JudgeBot judge = new JudgeBot();
        try {
            BotRunner.runBot(judge, "testCredentialsErreur.properties");
        } catch (Exception e) {}
        assertThat(judge).isNotNull();
    }

    @Test
    public void test_runBot_NoJudge() {
        try {
            BotRunner.runBot(null, "testCredentialsErreur.properties");
        } catch (Exception e) {
            assertThat(e).isNotNull();
        }
    }
}

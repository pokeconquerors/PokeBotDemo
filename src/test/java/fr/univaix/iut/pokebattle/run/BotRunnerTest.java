package fr.univaix.iut.pokebattle.run;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.tuse.TUSEException;

public class BotRunnerTest {

    @Test
    public void test_Constructeur() {
        InputStream is = BotRunner.getResourceAsStream("testCredentials.properties");
        assertThat(is).isNotNull();
    }

    @Test(expected=NullPointerException.class)
    public void test_runBot_error() throws TUSEException, IOException {
        JudgeBot judge = new JudgeBot();
        BotRunner.runBot(judge, "testCredentialsErreur.properties");
    }

    @Test(expected=Exception.class)
    public void test_runBot_noerror() throws TUSEException, IOException {
        JudgeBot judge = new JudgeBot();
        BotRunner.runBot(judge, "testCredentialsErreur.properties");
    }

    @Test(expected=Exception.class)
    public void test_runBot_NoJudge() throws TUSEException, IOException {
            BotRunner.runBot(null, "testCredentialsErreur.properties");
    }
}

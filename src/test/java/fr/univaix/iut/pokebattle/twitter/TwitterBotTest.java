package fr.univaix.iut.pokebattle.twitter;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.run.PokemonMain;
import fr.univaix.iut.pokebattle.tuse.Credentials;
import fr.univaix.iut.pokebattle.tuse.TUSEException;
import static org.fest.assertions.Assertions.assertThat;

public class TwitterBotTest {

	static InputStream getResourceAsStream(String fileName) {
        return PokemonMain.class.getClassLoader().getResourceAsStream(fileName);
    }
	
	@Test
	public void test_contructor() throws IOException, TUSEException {
		InputStream inputStream = getResourceAsStream("testCredentials.properties");
        Credentials credentials = Credentials.loadCredentials(inputStream);
		TwitterBot twitterbot = new TwitterBot(new JudgeBot(), credentials);
		twitterbot.startBot();
		assertThat(twitterbot).isNotNull();
	}

}

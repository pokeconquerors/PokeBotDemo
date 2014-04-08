package fr.univaix.iut.pokebattle.run;

import fr.univaix.iut.pokebattle.bot.Bot;
import fr.univaix.iut.pokebattle.tuse.Credentials;
import fr.univaix.iut.pokebattle.tuse.TUSEException;
import fr.univaix.iut.pokebattle.twitter.TwitterBot;

import java.io.IOException;
import java.io.InputStream;

public final class BotRunner {
    private static BotRunner instance = null;

    private BotRunner() { }

    public static BotRunner getInstance() {
        return (instance != null ? instance : new BotRunner());
    }

    public static void runBot(final Bot bot, final String credentialsFileName)
            throws TUSEException, IOException {
        InputStream inputStream = getResourceAsStream(credentialsFileName);
        Credentials credentials = Credentials.loadCredentials(inputStream);
        TwitterBot twitterBot = new TwitterBot(bot, credentials);
        twitterBot.startBot();
    }

    static InputStream getResourceAsStream(final String fileName) {
        return PokemonMain.class.getClassLoader().getResourceAsStream(fileName);
    }
}

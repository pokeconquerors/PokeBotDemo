package fr.univaix.iut.pokebattle.run;

import java.io.IOException;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.data.DataReadObject;
import fr.univaix.iut.pokebattle.tuse.TUSEException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PokemonMain {
    private static final Logger LOGGER = LoggerFactory
                                               .getLogger(PokemonMain.class);

    public static void definePokedexFile(final String pokedexFile) {
        String tmpPokedexFile = (pokedexFile != null ? pokedexFile
                : "pokedex.json");
        DataReadObject.getInstance().setPokedexFile(tmpPokedexFile);
    }

    private PokemonMain() {

    }

    public static void main(final String[] args) {
        String pokedexFile = getPokedexFile(args);
        definePokedexFile(pokedexFile);

        try {
            BotRunner.runBot(new JudgeBot(), "twitter4j.properties");
        } catch (TUSEException | IOException e) {
            LOGGER.error("Erreur sérieuse dans le BotRunner", e);
        }
    }

    private static String getPokedexFile(final String[] args) {
        return getFromArgs(args, "pokedexFile=");
    }

    private static String getFromArgs(final String[] args, final String element) {
        for (String string : args) {
            if (string.contains(element)) { return string.replaceAll(element,
                    ""); }
        }
        return null;
    }
}

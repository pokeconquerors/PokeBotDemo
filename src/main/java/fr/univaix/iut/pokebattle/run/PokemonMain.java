package fr.univaix.iut.pokebattle.run;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.data.DataReadObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PokemonMain {
    private static PokemonMain  instance;
    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonMain.class);

    public static void definePokedexFile(final String pokedexFile) {
        String tmpPokedexFile = (pokedexFile != null
                ? pokedexFile
                : "pokedex.json");
        DataReadObject.getInstance().setPokedexFile(tmpPokedexFile);
    }

    private PokemonMain() { }

    public static PokemonMain getInstance() {
        return (instance != null
                ? instance
                : new PokemonMain());
    }

    public static void main(final String[] args) {
        String pokedexFile = getPokedexFile(args);
        definePokedexFile(pokedexFile);
        String propertiesFile = getPropertiesFile(args);
        if (propertiesFile == null) {
            propertiesFile = "twitter4j.properties";
        }
        try {
            BotRunner.runBot(new JudgeBot(), propertiesFile);
        } catch (Exception e) {
            LOGGER.error("Erreur sérieuse dans le BotRunner", e);
        }
    }

    private static String getPropertiesFile(final String[] args) {
        return getFromArgs(args, "propertiesFile=");
    }

    private static String getPokedexFile(final String[] args) {
        return getFromArgs(args, "pokedexFile=");
    }

    private static String getFromArgs(final String[] args, final String element) {
        for (String string : args) {
            if (string.contains(element)) {
                return string.replaceAll(element, "");
            }
        }
        return null;
    }
}

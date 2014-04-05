package fr.univaix.iut.pokebattle.run;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.data.DataReadObject;
import fr.univaix.iut.pokebattle.tuse.TUSEException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PokemonMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonMain.class);

    public static void definePokedexFile(String pokedexFile) {
    	String tmpPokedexFile = (pokedexFile != null ? pokedexFile : "pokedex.json");
    	DataReadObject.getInstance().setPokedexFile(tmpPokedexFile);     	
    }
    
    public PokemonMain() {
    	
    }
    
    public static void main(String[] args) {
		String pokedexFile = getPokedexFile(args);
		definePokedexFile(pokedexFile);

		try {
            BotRunner.runBot(new JudgeBot(), "twitter4j.properties");
        } catch (TUSEException e) {
            LOGGER.error("Erreur sérieuse dans le BotRunner", e);
        }
    }

	private static String getPokedexFile(String[] args) {
		return getFromArgs(args, "pokedexFile=");
	}
	
	private static String getFromArgs(String[] args, String element) {
		for (String string : args) {
			if(string.contains(element)) {
				return string.replaceAll(element,"");
			}
		}
		return null;
	}
}

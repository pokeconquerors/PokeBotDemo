package fr.univaix.iut.pokebattle.run;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.data.DataReadObject;
import fr.univaix.iut.pokebattle.tuse.TUSEException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PokemonMain {
    private static final Logger LOGGER = LoggerFactory.getLogger(PokemonMain.class);

    private PokemonMain() {

    }

    private static void initialisation() {
    	DataReadObject.getInstance().setPokedexFile("pokedex.json");     	
    }
    
    public static void main(String[] args) {
    	try {
    		initialisation();
    	}
    	catch (Exception e) {
    		LOGGER.error("Erreur d'initialisation\n" + e.getMessage());
    	}
    	try {
            BotRunner.runBot(new JudgeBot(), "twitter4j.properties");
        } catch (TUSEException e) {
            LOGGER.error("Erreur sérieuse dans le BotRunner", e);
        }
    }
}

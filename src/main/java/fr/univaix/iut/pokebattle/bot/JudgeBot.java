package fr.univaix.iut.pokebattle.bot;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerAreneCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeHireCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeNbPLostCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeValidateFightCell;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeBot implements Bot {
	private boolean inFight = false;
	private String arene = null;
	private List<String[]> pokemons = new ArrayList<String[]>();
	private long id;
	private Twitter twitter;

	
	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

	public boolean isInFight() {
		return inFight;
	}

	public void setInFight(boolean inFight) {
		this.inFight = inFight;
	}

	public void pushPokemon(String nomPokemon, String nomProprio, String level, String xp) {
		pokemons.add(new String[] { nomPokemon, nomProprio, level, xp });
	}
	
	public String getWinnerPokemon(String pokemonPerdant) {
		for (String[] tmpPokemons : pokemons) {
			if (!tmpPokemons[0].equals(pokemonPerdant)) {
				return tmpPokemons[0];
			}
		}
		return null;
	}
	
	public String getElementInList(String pokemon, int indice) {
		for (String[] tmpPokemons : pokemons) {
			if (tmpPokemons[0].equals(pokemon)) {
				return tmpPokemons[indice];
			}
		}
		return null;
	}

	public String getPokemon(String pokemon) {
		return getElementInList(pokemon, 0);
	}
	
	public String getProprietaire(String pokemon) {
		return getElementInList(pokemon, 1);
	}
	
	public String getLevel(String pokemon) {
		return getElementInList(pokemon, 2);
	}
	
	public String getXP(String pokemon) {
		return getElementInList(pokemon, 3);
	}

	public JudgeBot() {

	}

	public JudgeBot(String arene) {
		this.arene = arene;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * List of smartcell the questions go through to find an answer.
	 */
	private final SmartCell[] smartCells = new SmartCell[] {
			new JudgeHireCell(this), new JudgeValidateFightCell(this),
			new JudgeNbPLostCell(this), new JudgeAnswerAreneCell(this),
			new JudgeAnswerCell() };

	/**
	 * Ask something to Bot, it will respond to you.
	 * 
	 * @param question
	 *            The question you ask.
	 * @return An answer... or null if it doesn't get it.
	 */

	@Override
	public String ask(Tweet question) {
		for (SmartCell cell : smartCells) {
			String answer = cell.ask(question);
			if (answer != null) {
				return answer;
			}
		}
		return null;
	}

	public void setArene(String arene){
		this.arene = arene;
	}

	public String getArene(){
		return arene;		
	}
	public void update() throws TwitterException {
		getTwitter().updateProfile("Pokejuge", "", "","gym : " + getArene());
	}
}

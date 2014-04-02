package fr.univaix.iut.pokebattle.bot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.smartcell.JudgeAlwaysAnswersCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerAreneCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerNbFightCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerWinnerCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeHireCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerValidAttaque;
import fr.univaix.iut.pokebattle.smartcell.JudgeValidateFightCell;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeBot implements Bot {
	private boolean inFight = false;
	private String arene = null;
	private List<String[]> pokemons = new ArrayList<String[]>();
	private long id;
	private Twitter twitter;
	private List<Date> date5fight = new ArrayList<>();
	private static final int UNE_HEURE = 3600000;

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

	public void pushPokemon(String nomPokemon, String nomProprio, String level,
			String xp) {
		pokemons.add(new String[] { nomPokemon, nomProprio, level, xp });
	}

	public String getOtherPokemon(String pokemonPerdant) {
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
			new JudgeAnswerWinnerCell(this), new JudgeAnswerNbFightCell(this),
			new JudgeHireCell(this), new JudgeValidateFightCell(this),
			new JudgeAnswerValidAttaque(this), new JudgeAnswerAreneCell(this),
			new JudgeAnswerCell(), new JudgeAlwaysAnswersCell() };

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

	public void setArene(String arene) {
		this.arene = arene;
	}

	public String getArene() {
		return arene;
	}

	public void update() throws TwitterException {
		twitter.updateProfile("Pokejuge", "", "", "gym : " + getArene());
	}

	public boolean hasAlreadyDone5Fights() {
		if (date5fight == null)
			return false;
		if (date5fight.size() == 5)
			return true;
		return false;
	}

	public boolean isMoreThanAnHour(Date date1, Date date2) {
		if (date1.getTime() > date2.getTime() + UNE_HEURE) {
			return true;
		}
		return false;
	}

	public void updateDateList(Date datetweet) {
		for (int i = 0; i < date5fight.size(); i++) {
			if (isMoreThanAnHour(datetweet, date5fight.get(i))) {
				date5fight.remove(0);
			}
		}
	}

	public List<Date> getDate5fight() {
		return date5fight;
	}

	public void setDate5fight(List<Date> l) {
		this.date5fight = l;
	}

	public void addDate5fight(Date d) {
		date5fight.add(d);
	}
}

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
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswersAfterHimSelfCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeHireCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerValidAttaqueCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeInfoFirstOpponentsCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeNbRoundsCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeOverBidCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeValidateFightCell;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeBot implements Bot {
	private boolean				inFight				= false;
	private String				arene				= null;
	private List<String[]>		pokemons			= new ArrayList<String[]>();
	private long				id;
	private Twitter				twitter;
	private List<Date>			date5fight			= new ArrayList<>();
	private static final int	UNE_HEURE			= 3600000;
	private int					Nb_Rounds_en_cours	= 0;
	private int					salaire				= 0;
	private String				screenName			= null;

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

	public String getOtherPokemon(String pokemonPerdant) {
		for (String[] tmpPokemons : pokemons) {
			if (!tmpPokemons[0].equals(pokemonPerdant)) { return tmpPokemons[0]; }
		}
		return null;
	}

	public String getElementInList(String testeur, int indice, int caseToLook) {
		for (String[] tmpPokemons : pokemons) {
			if (tmpPokemons[caseToLook].equals(testeur)) { return tmpPokemons[indice]; }
		}
		return null;
	}

	public String getElementInList(String pokemon, int indice) {
		return getElementInList(pokemon, indice, 0);
	}

	public String getPokemonFromList(String pokemon) {
		return getElementInList(pokemon, 0);
	}

	public String getProprietaireFromList(String pokemon) {
		return getElementInList(pokemon, 1);
	}

	public String getLevelFromList(String pokemon) {
		return getElementInList(pokemon, 2);
	}

	public String getXPFromList(String pokemon) {
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
	private final SmartCell[]	smartCells	= new SmartCell[] { new JudgeOverBidCell(this), new JudgeInfoFirstOpponentsCell(this),
			new JudgeAnswersAfterHimSelfCell(this), new JudgeNbRoundsCell(this), new JudgeAnswerWinnerCell(this),
			new JudgeAnswerNbFightCell(this), new JudgeHireCell(this), new JudgeValidateFightCell(this),
			new JudgeAnswerValidAttaqueCell(this), new JudgeAnswerAreneCell(this), new JudgeAnswerCell(), };

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
			if (answer != null) { return answer; }
		}
		return new JudgeAlwaysAnswersCell().ask(question);
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
		if (date5fight.size() == 5)
			return true;
		return false;
	}

	public boolean isMoreThanAnHour(Date date1, Date date2) {
		if (date1.getTime() > date2.getTime() + UNE_HEURE) { return true; }
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

	public int getNb_Rounds_en_cours() {
		return Nb_Rounds_en_cours;
	}

	public void IncrNb_Rounds_en_cours() {
		++Nb_Rounds_en_cours;
	}

	public void ReinitNb_Rounds_en_cours() {
		Nb_Rounds_en_cours = 0;
	}

	public boolean isInterestedBy(int montant) {
		if (montant >= (getSalaire() + 100) && montant <= (getSalaire() + 1000))
			return true;
		return false;
	}

	public int getSalaire() {
		return salaire;
	}

	public void setSalaire(int montant) {
		salaire = montant;
	}

	private String getFromTab(int indice, String text) {
		String[] tab = text.split(" ");
		return tab[indice];
	}

	public String getPoke(String textToSplit) {
		return getFromTab(4, textToSplit);
	}

	public String getCallForNextRound() {
		return "Round #" + getNb_Rounds_en_cours() + " /cc " + pokemons.get(0)[1] + " " + pokemons.get(0)[0] + " "
				+ pokemons.get(1)[1] + " " + pokemons.get(1)[0];
	}

	@Override
	public boolean isTimeToNextRound(String text) {
		try {
			String tmpText = text.toLowerCase();
			String[] pokemon1 = pokemons.get(0);
			String[] pokemon2 = pokemons.get(1);
			String round = "#" + getNb_Rounds_en_cours();
			if(tmpText.contains("round "+round))
				return false;
			if (tmpText.contains(pokemon1[0].toLowerCase()) && tmpText.contains(pokemon1[1].toLowerCase())
					&& tmpText.contains(round.toLowerCase()))
				return true;
			if (tmpText.contains(pokemon2[0].toLowerCase()) && tmpText.contains(pokemon2[1].toLowerCase())
					&& tmpText.contains(round.toLowerCase()))
				return true;
			return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	@Override
	public String getScreenName() {
		return screenName;
	}
}

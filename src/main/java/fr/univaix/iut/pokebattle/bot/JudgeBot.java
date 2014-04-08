package fr.univaix.iut.pokebattle.bot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.smartcell.JudgeAlwaysAnswersCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerAreneCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerHelpCell;
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
	private static final int	POSITION_XP_IN_LIST			= 3;
	private static final int	POSITION_LEVEL_IN_LIST		= 2;
	private static final int	POSITION_PROPRIO_IN_LIST	= 1;
	private static final int	POSITION_POKEMON_IN_LIST	= 0;
	private static final int	NULL						= 0;
	private static final int	ONE_POKEMON					= 1;
	private static final int	POSITION_POKEMON_IN_TAB		= 4;
	private static final int	MONTANT_MAXIMUM_OVERBID		= 1000;
	private static final int	MONTANT_MINIMUM_OVERBID		= 100;
	private static final int	NB_MAX_FIGHT				= 5;
	private boolean				inFight						= false;
	private String				arene						= null;
	private List<String[]>		pokemons					= new ArrayList<String[]>();
	private long				id;
	private Twitter				twitter;
	private List<Date>			date5fight					= new ArrayList<>();
	private static final int	UNE_HEURE					= 3600000;
	private int					nbRoundsEnCours				= 0;
	private int					salaire						= 0;
	private String				screenName					= null;

	public final Twitter getTwitter() {
		return twitter;
	}

	public final void setTwitter(final Twitter twitter) {
		this.twitter = twitter;
	}

	public final boolean isInFight() {
		return inFight;
	}

	public final void setInFight(final boolean inFight) {
		this.inFight = inFight;
	}

	public final void pushPokemon(final String nomPokemon, final String nomProprio,
			final String level, final String xp) {
		pokemons.add(new String[] { nomPokemon, nomProprio, level, xp });
	}

	public final String getOtherPokemon(final String pokemonPerdant) {
		for (String[] tmpPokemons : pokemons) {
			if (!tmpPokemons[0].contains(pokemonPerdant)) {
				return tmpPokemons[0];
			}
		}
		return null;
	}

	public final String getElementInList(final String testeur, final int indice,
			final int caseToLook) {
		for (String[] tmpPokemons : pokemons) {
			if (tmpPokemons[caseToLook].equals(testeur)) {
				return tmpPokemons[indice];
			}
		}
		return null;
	}

	public final String getElementInList(final String pokemon, final int indice) {
		return getElementInList(pokemon, indice, 0);
	}

	public final String getPokemonFromList(final String pokemon) {
		return getElementInList(pokemon, POSITION_POKEMON_IN_LIST);
	}

	public final String getProprietaireFromList(final String pokemon) {
		return getElementInList(pokemon, POSITION_PROPRIO_IN_LIST);
	}

	public final String getLevelFromList(final String pokemon) {
		return getElementInList(pokemon, POSITION_LEVEL_IN_LIST);
	}

	public final String getXPFromList(final String pokemon) {
		return getElementInList(pokemon, POSITION_XP_IN_LIST);
	}

	public JudgeBot() {

	}

	public JudgeBot(final String arene) {
		this.arene = arene;
	}

	@Override
	public final long getId() {
		return id;
	}

	@Override
	public final void setId(final long id) {
		this.id = id;
	}

	private final SmartCell[]	smartCells	= new SmartCell[] { new JudgeOverBidCell(this),
			new JudgeInfoFirstOpponentsCell(this), new JudgeAnswersAfterHimSelfCell(this),
			new JudgeNbRoundsCell(this), new JudgeAnswerWinnerCell(this),
			new JudgeAnswerNbFightCell(this), new JudgeHireCell(this),
			new JudgeValidateFightCell(this), new JudgeAnswerValidAttaqueCell(this),
			new JudgeAnswerAreneCell(this), new JudgeAnswerCell(), new JudgeAnswerHelpCell(this) };

	public SmartCell[] getSmartCells() {
		return smartCells;
	}

	@Override
	public final String ask(final Tweet question) {
		for (SmartCell cell : smartCells) {
			String answer = cell.ask(question);
			if (answer != null) {
				return answer;
			}
		}
		return new JudgeAlwaysAnswersCell().ask(question);
	}

	public final void setArene(final String arene) {
		this.arene = arene;
	}

	public final String getArene() {
		return arene;
	}

	public final void update() throws TwitterException {
		twitter.updateProfile("Pokejuge", "", "", "gym : " + getArene());
	}

	public final boolean hasAlreadyDone5Fights() {
		if (date5fight.size() == NB_MAX_FIGHT) {
			return true;
		}
		return false;
	}

	public final boolean isMoreThanAnHour(final Date date1, final Date date2) {
		if (date1.getTime() > date2.getTime() + UNE_HEURE) {
			return true;
		}
		return false;
	}

	public final void updateDateList(final Date datetweet) {
		for (int i = 0; i < date5fight.size(); i++) {
			if (isMoreThanAnHour(datetweet, date5fight.get(i))) {
				date5fight.remove(0);
			}
		}
	}

	public final List<Date> getDate5fight() {
		return date5fight;
	}

	public final void setDate5fight(final List<Date> l) {
		this.date5fight = l;
	}

	public final void addDate5fight(final Date d) {
		date5fight.add(d);
	}

	public final int getNbRoundsencours() {
		return nbRoundsEnCours;
	}

	public final void incrNbRoundsEnCours() {
		++nbRoundsEnCours;
	}

	public final void reinitNbRoundsEnCours() {
		nbRoundsEnCours = 0;
	}

	public final boolean isInterestedBy(final int montant) {
		if (isSupThanMinimumAccepted(montant) && isInfThanMaximumAccepted(montant)) {
			return true;
		}
		return false;
	}

	private boolean isInfThanMaximumAccepted(final int montant) {
		return montant <= (getSalaire() + MONTANT_MAXIMUM_OVERBID);
	}

	private boolean isSupThanMinimumAccepted(final int montant) {
		return montant >= (getSalaire() + MONTANT_MINIMUM_OVERBID);
	}

	public final int getSalaire() {
		return salaire;
	}

	public final void setSalaire(final int montant) {
		salaire = montant;
	}

	private String getFromTab(final int indice, final String text) {
		String[] tab = text.split(" ");
		return tab[indice];
	}

	public final String getPoke(final String textToSplit) {
		return getFromTab(POSITION_POKEMON_IN_TAB, textToSplit);
	}

	public final String getCallForNextRound() {
		return "Round #" + getNbRoundsencours() + " /cc " + pokemons.get(0)[1] + " "
				+ pokemons.get(0)[0] + " " + pokemons.get(1)[1] + " " + pokemons.get(1)[0];
	}

	@Override
	public final boolean isTimeToNextRound(final String text) {
		if (isPokemonsNull()) {
			return false;
		}
		String tmpText = text.toLowerCase();
		String[] pokemon1 = pokemons.get(0);
		if (isPokemonsContainOnePokemon()) {
			return false;
		}
		String[] pokemon2 = pokemons.get(1);
		String round = "#" + getNbRoundsencours();
		if (tmpText.contains("round " + round)) {
			return false;
		}
		if (isContainIn(tmpText, pokemon1[0]) && isContainIn(tmpText, pokemon1[1])
				&& isContainIn(tmpText, round)) {
			return true;
		}
		if (isContainIn(tmpText, pokemon2[0]) && isContainIn(tmpText, pokemon2[1])
				&& isContainIn(tmpText, round)) {
			return true;
		}
		return false;
	}

	private boolean isPokemonsContainOnePokemon() {
		return pokemons.size() == ONE_POKEMON;
	}

	private boolean isContainIn(final String tmpText, final String pokemon) {
		return tmpText.toLowerCase().contains(pokemon.toLowerCase());
	}

	private boolean isPokemonsNull() {
		return pokemons == null || pokemons.size() <= NULL;
	}

	@Override
	public final void setScreenName(final String screenName) {
		this.screenName = screenName;
	}

	@Override
	public final String getScreenName() {
		return screenName;
	}
}

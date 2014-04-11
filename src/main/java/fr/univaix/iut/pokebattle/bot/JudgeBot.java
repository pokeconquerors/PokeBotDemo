package fr.univaix.iut.pokebattle.bot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswersAlwaysCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswersGymCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswersHelloCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswersHelpCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswersFightsCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswersWinnerCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswersAfterHimSelfCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswersHireCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswersValidAttaqueCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeGetFightWithCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswersNumRoundCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswersOverBidCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswersStartFightCell;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeBot implements Bot {
    private static final int POSITION_PLAY_NEXT_ROUND_IN_LIST = 4;
    private static final int POSITION_XP_IN_LIST              = 3;
    private static final int POSITION_LEVEL_IN_LIST           = 2;
    private static final int POSITION_PROPRIO_IN_LIST         = 1;
    private static final int POSITION_POKEMON_IN_LIST         = 0;
    private static final int NULL                             = 0;
    private static final int ONE_POKEMON                      = 1;
    private static final int POSITION_POKEMON_IN_TAB          = 4;
    private static final int MONTANT_MAXIMUM_OVERBID          = 1000;
    private static final int MONTANT_MINIMUM_OVERBID          = 100;
    private static final int NB_MAX_FIGHT                     = 5;
    private boolean          inFight                          = false;
    private String           arene                            = null;
    private List<String[]>   pokemons                         = new ArrayList<String[]>();
    private long             id;
    private Twitter          twitter;
    private List<Date>       date5fight                       = new ArrayList<>();
    private static final int UNE_HEURE                        = 3600000;
    private int              idRoundsEnCours                  = 0;
    private int              salaire                          = 0;
    private String           screenName                       = null;
    private String           tweetMemoryResultat              = null;
    private Boolean          wait                             = false;

    public final Twitter getTwitter() {
        return twitter;
    }

    public final void setTwitter(final Twitter twitter) {
        this.twitter = twitter;
    }

    public final void setTweetMemoryResultat(final String tweetMemoryResultat) {
        this.tweetMemoryResultat = tweetMemoryResultat;
    }

    public final String getTweetMemoryResultat() {
        return this.tweetMemoryResultat;
    }

    public final void setInFight(final boolean inFight) {
        this.inFight = inFight;
    }

    public final boolean isInFight() {
        return inFight;
    }

    public final void pushPokemon(final String nomPokemon, final String nomProprio,
            final String level, final String xp, final boolean playNextRound) {
        pokemons.add(new String[] {nomPokemon, nomProprio, level, xp,
                Boolean.toString(playNextRound)});
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
            if (tmpPokemons[caseToLook].contains(testeur)) {
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

    public final Boolean getPlayNextRound(final String pokemon) {
        String resultat = getElementInList(pokemon, POSITION_PLAY_NEXT_ROUND_IN_LIST);
        return (resultat.equals("true")
                ? true
                : false);
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

    private final SmartCell[] smartCells = new SmartCell[] {new JudgeAnswersOverBidCell(this),
            new JudgeGetFightWithCell(this), new JudgeAnswersAfterHimSelfCell(this),
            new JudgeAnswersNumRoundCell(this), new JudgeAnswersWinnerCell(this),
            new JudgeAnswersFightsCell(this), new JudgeAnswersHireCell(this),
            new JudgeAnswersStartFightCell(this), new JudgeAnswersValidAttaqueCell(this),
            new JudgeAnswersGymCell(this), new JudgeAnswersHelloCell(),
            new JudgeAnswersHelpCell(this)};

    public final SmartCell[] getSmartCells() {
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
        return new JudgeAnswersAlwaysCell(this).ask(question);
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

    public final int getIdRoundsEnCours() {
        return idRoundsEnCours;
    }

    public final void incrIdRoundsEnCours() {
        if (!wait) {
            ++idRoundsEnCours;
        }
    }

    public final void reInitIdRoundsEnCours() {
        idRoundsEnCours = 0;
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
        return "Round #" + getIdRoundsEnCours() + " /cc " + pokemons.get(0)[1] + " "
                + pokemons.get(0)[0] + " " + pokemons.get(1)[1] + " " + pokemons.get(1)[0];
    }

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
        String round = "#" + getIdRoundsEnCours();
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

    @Override
    public final boolean isAnInterestingTweetOfMe(final String text) {
        return isTimeToNextRound(text) || isTimeToEndRound(text);
    }

    public final boolean isTimeToEndRound(final String text) {
        return wait;
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

    public final String getCallForNextResult() {
        return tweetMemoryResultat;
    }

    public final boolean getWait() {
        return wait;
    }

    public final void setWait(final boolean wait) {
        this.wait = wait;
    }

    public final void setSkipNextRound(final String pokemon, final boolean playNextRound) {
        for (String[] tmpPokemon : pokemons) {
            if (tmpPokemon[0].contains(pokemon)) {
                pokemons.remove(tmpPokemon);
                pushPokemon(tmpPokemon[0], tmpPokemon[1], tmpPokemon[2], tmpPokemon[3],
                        playNextRound);
                break;
            }
        }
    }
}

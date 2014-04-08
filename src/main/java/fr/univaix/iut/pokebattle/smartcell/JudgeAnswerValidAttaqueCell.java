package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.data.DataObjectAttack;
import fr.univaix.iut.pokebattle.data.DataReadObject;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class JudgeAnswerValidAttaqueCell implements SmartCell {
	private static final int	POSITION_CC	= 3;
	private static final int	POSITION_ATTAQUE	= 2;
	private static final int	POSITION_NUM_ROUND	= 7;
	private JudgeBot	owner;

	public JudgeAnswerValidAttaqueCell(final JudgeBot owner) {
		this.owner = owner;
	}

	DataReadObject	dro	= DataReadObject.getInstance();

	public final String getElementInArray(final String text, final int indice) {
		String[] tab = text.split(" ");
		return tab[indice];
	}

	public final String ask(final Tweet question) {

		if (isNotNull(question) && isALaunchAttackFromPokemon(question)) {
			String nomPokemon = question.getScreenName();
			String adversairePokemon = owner.getOtherPokemon(nomPokemon);
			owner.incrIdRoundsEnCours();
			if (isACorrectTweet(question) && isACorrectAttack(question)) {
				return "" + adversairePokemon + " -10pv /cc " + "@"
						+ owner.getProprietaireFromList(adversairePokemon)+ " #" + owner.getIdRoundsEnCours();
			}
			else {
				return "" + adversairePokemon + " -0pv /cc " + "@"
						+ owner.getProprietaireFromList(adversairePokemon)+ " #" + owner.getIdRoundsEnCours()
						+ (isWeirdSmiley(question) ? "": getPenalityMessage());
			}
		}
		return null;
	}

	private String getPenalityMessage() {
		return " Tu ne respectes pas la convention d'attaque, tu passes le prochain tour";
	}

	private boolean isWeirdSmiley(Tweet question) {
		
		return question.getText().contains("o_O ?");
	}

	private boolean isACorrectTweet(final Tweet question) {
		return isGoodRound(question) && isNotWeirdSmiley(question);
	}

	private boolean isGoodRound(final Tweet question) {
		return getNumRound(question).equals("" + owner.getIdRoundsEnCours());
	}

	private boolean isNotWeirdSmiley(final Tweet question) {
		return !isWeirdSmiley(question);
	}

	private String getNumRound(Tweet question) {
		return getElementInArray(question.getText(), POSITION_NUM_ROUND).substring(1);
	}

	private boolean isACorrectAttack(final Tweet question) {
		String nomAttaque = getAttaque(question);
		String nomPokemon = question.getScreenName();
		DataObjectAttack attaque = dro.getAttaque(nomPokemon, nomAttaque);
		return attaque != null;
	}

	private String getAttaque(Tweet question) {
		return getElementInArray(question.getText(), POSITION_ATTAQUE).substring(1);
	}

	private boolean isNotNull(final Tweet question) {
		return question.getScreenName() != null;
	}

	private boolean isALaunchAttackFromPokemon(final Tweet question) {
		return question.getText().matches(getKeyWord()) && getCC(question).equals("/cc");
	}

	private String getCC(Tweet question) {
		return getElementInArray(question.getText(), POSITION_CC);
	}
	
	@Override
	public final String getKeyWord() {
		return "@.+ #(?i)attack #.+ @.+.*";
	}

}

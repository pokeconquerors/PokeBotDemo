package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.data.DataObjectAttack;
import fr.univaix.iut.pokebattle.data.DataReadObject;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class JudgeAnswerValidAttaque implements SmartCell {
	private JudgeBot owner;

	public JudgeAnswerValidAttaque(JudgeBot owner) {
		this.owner = owner;
	}

	DataReadObject dro = DataReadObject.getInstance();

	public String getElementInArray(String text, int indice) {
		System.out.println(text);
		String[] tab = text.split(" ");
		return tab[indice];
	}

	public String ask(Tweet question) {

		if (isNotNull(question) && isALaunchAttack(question) ) {	
			String nomPokemon = getPokemon(question);
			if (isACorrectAttack(question)) {
				return "@" + owner.getOtherPokemon(nomPokemon) + " -10pv /cc "
						+ "@" + question.getScreenName();
			} else {
				return "@" + owner.getOtherPokemon(nomPokemon) + " -0pv /cc "
						+ "@" + question.getScreenName();
			}

		}
		return null;
	}

	private boolean isACorrectAttack(Tweet question) {
		String nomAttaque = getAttaque(question);
		String nomPokemon = getPokemon(question);
		DataObjectAttack attaque = dro.getAttaque(nomPokemon, nomAttaque);
		return attaque != null;
	}

	private String getPokemon(Tweet question) {
		return getElementInArray(question.getText(), 0)
				.substring(1);
	}

	private String getAttaque(Tweet question) {
		return getElementInArray(question.getText(), 2)
				.substring(1);
	}

	private boolean isNotNull(Tweet question) {
		return question.getScreenName() != null;
	}

	private boolean isALaunchAttack(Tweet question) {
		return question.getText().matches("@.+ #(?i)attack #.+ @.+.*");
	}

}

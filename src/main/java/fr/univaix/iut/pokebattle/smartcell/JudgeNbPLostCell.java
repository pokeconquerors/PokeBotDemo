package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class JudgeNbPLostCell implements SmartCell {
	private JudgeBot owner;
	
	public JudgeNbPLostCell (JudgeBot owner) {
		this.owner = owner;
	}
	
	public String getPokemon(String text) {
		String[] tab = text.split(" ");
		return tab[0];
	}

	public String ask(Tweet question) {

		if (question.getText().toLowerCase().contains("#attack") && question.getScreenName() != null) {
			System.out.println(getPokemon(question.getText()) + " -10 pv /cc "
					+ "@" + question.getScreenName());
			return getPokemon(question.getText()) + " -10 pv /cc " + "@"
					+ question.getScreenName();
		}
		return null;
	}

}

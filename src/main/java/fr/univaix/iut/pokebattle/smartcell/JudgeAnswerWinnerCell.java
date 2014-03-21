package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class JudgeAnswerWinnerCell implements SmartCell {
	private JudgeBot owner;
	
	public JudgeAnswerWinnerCell (JudgeBot owner) {
		this.owner = owner;
	}
	
	public String getPokemon(String text) {
		String[] tab = text.split(" ");
		return tab[0];
	}
	

	
	public String ask(Tweet question) {
	
		if (question.getText().toLowerCase().contains("#ko") && question.getScreenName() != null) {
			System.out.println("@" + owner.getWinnerPokemon(question.getScreenName()) + " #Win" );
			return "@" + owner.getWinnerPokemon(question.getScreenName()) + " #Win";
		}
		return null;
	}

}

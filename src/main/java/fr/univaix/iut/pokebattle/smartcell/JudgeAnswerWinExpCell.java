package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class JudgeAnswerWinExpCell implements SmartCell {
	private JudgeBot owner;
	
	public JudgeAnswerWinExpCell (JudgeBot owner) {
		this.owner = owner;
	}

	
	public String ask(Tweet question) {
		
		if (question.getText().toLowerCase().contains("#ko") && question.getScreenName() != null && owner.isInFight() ) {
			System.out.println("@" + owner.getWinnerPokemon(question.getScreenName()) + " #Win" );
			return "@" + owner.getWinnerPokemon(question.getScreenName()) + "#stat #XP ?";				
		}
		return null;
	}

}

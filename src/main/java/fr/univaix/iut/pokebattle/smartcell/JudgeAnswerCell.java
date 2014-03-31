package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class JudgeAnswerCell implements SmartCell {
	
	public String ask(Tweet question) {

		if (question.getText().toLowerCase().contains(" salut!")
				&& question.getScreenName() != null) {			
			return "@" + question.getScreenName()
					+ " Salisalut très cher voisin !";
		}
		return null;
	}
}

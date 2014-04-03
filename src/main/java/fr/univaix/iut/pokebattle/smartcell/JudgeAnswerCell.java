package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class JudgeAnswerCell implements SmartCell {
	
	public String ask(Tweet question) {
		if (isNotNull(question) && isAHello(question)) {			
			return "@" + question.getScreenName()
					+ " Salisalut très cher voisin !";
		}
		return null;
	}

	private boolean isNotNull(Tweet question) {
		return question.getScreenName() != null;
	}

	private boolean isAHello(Tweet question) {
		return question.getText().matches(".*\\s+(?i)salut\\s*!.*");
		//("(.)*\\s+(?i)salut\\[\\^\\S\\]*\\s*!*(.)*");//(.)*\s+salut[^\S]*\s*\!*(.)*
		
	}
}

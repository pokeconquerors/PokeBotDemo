
package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersAfterHimSelfCell implements SmartCell{
	private JudgeBot owner = null;
	
	public JudgeAnswersAfterHimSelfCell(JudgeBot owner) {
		this.owner = owner;
	}

	@Override
	public String ask(Tweet question) {
		if(isNotNull(question) && isTweetOfMe(question)) {
			if(owner.isTimeToNextRound(question.getText()))
				return owner.getCallForNextRound();
		}
		return null;
	}

	private boolean isTweetOfMe(Tweet question) {
		return question.getScreenName().toLowerCase().equals(owner.getScreenName().toLowerCase());
	}

	private boolean isNotNull(Tweet question) {
		return question.getScreenName() != null;
	}
}

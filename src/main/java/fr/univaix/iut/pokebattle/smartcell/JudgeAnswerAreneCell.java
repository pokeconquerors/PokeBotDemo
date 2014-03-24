package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswerAreneCell implements SmartCell {
	private JudgeBot owner;
	
	public JudgeAnswerAreneCell(JudgeBot owner) {
		this.owner = owner;
	}

	public String ask(Tweet question) {
		if ( question.getText().toLowerCase().contains(" gym?") && question.getScreenName() != null) {	
			if(owner.getArene() != null) {
				return "@" + question.getScreenName() + " my Gym is " + owner.getArene();
			}
			else {
				return "@" + question.getScreenName() + " no Gym";
			}	
		}
		return null;
	}
}
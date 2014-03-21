package fr.univaix.iut.pokebattle.smartcell;

import twitter4j.TwitterException;
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
				owner.setArene("no Gym");
				try {
					owner.update();
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "@" + question.getScreenName() + " no Gym";
			}	
		}
		return null;
	}
}
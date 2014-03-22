package fr.univaix.iut.pokebattle.smartcell;

import twitter4j.TwitterException;
import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeHireCell implements SmartCell {

	private JudgeBot owner;

	public JudgeHireCell(JudgeBot owner) {
		this.owner = owner;
	}

	public String ask(Tweet question) {
		if (question.getText().toLowerCase().contains("hire")) {
			if (owner.getArene() == null || owner.getArene() == "no Gym") {
				owner.setArene("@" + question.getScreenName());
				try {
					owner.update();
				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "@" + question.getScreenName() + " my gym is " + "@"
						+ question.getScreenName();
			} else {
				return "@" + question.getScreenName() + " " + owner.getArene()
						+ " is my owner";
			}
		}
		return null;
	}
}

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
		if (isNotNull(question) && isAnArena(question)) {	
			return getGymMessage(question);	
		}
		return null;
	}

	private String getGymMessage(Tweet question) {
		if(hasGym()) {
			return "@" + question.getScreenName() + " my Gym is " + owner.getArene();
		}
		owner.setArene("no Gym");
		System.out.println("Before catch");
		try {
			System.out.println("In try");
			owner.update();
		} 
		catch (Exception e) {
			System.out.println("In catch");
			e.printStackTrace();
		}
		System.out.println("After catch");
		return "@" + question.getScreenName() + " no Gym";
	}

	private boolean hasGym() {
		return owner.getArene() != null;
	}

	private boolean isNotNull(Tweet question) {
		return question.getScreenName() != null;
	}

	private boolean isAnArena(Tweet question) {
		return question.getText().matches(".*\\s+[gG][yY][mM]\\s*\\?.*");
	}
}
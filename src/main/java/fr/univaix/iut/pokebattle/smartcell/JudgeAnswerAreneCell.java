package fr.univaix.iut.pokebattle.smartcell;

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
		try {
			owner.update();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
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
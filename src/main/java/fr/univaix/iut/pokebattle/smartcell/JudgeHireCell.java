package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeHireCell implements SmartCell {

	private JudgeBot owner;

	public JudgeHireCell(JudgeBot owner) {
		this.owner = owner;
	}

	public String ask(Tweet question) {
		if (isNotNull(question) && isHiring(question)) {
			if (hasNoArena()) {
				owner.setArene("@" + question.getScreenName());
				updateOwnerDescription();
				return "@" + question.getScreenName() + " my gym is " + "@"
						+ question.getScreenName();
			} 
			else {
				return "@" + question.getScreenName() + " " + owner.getArene()
						+ " is my owner";
			}
		}
		return null;
	}

	private boolean hasNoArena() {
		return owner.getArene() == null;
	}

	private void updateOwnerDescription() {
		try {
			owner.update();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isNotNull(Tweet question) {
		return (question.getScreenName() != null);
	}

	private boolean isHiring(Tweet question) {
		return question.getText().matches(".*\\s+(?i)hire\\s*!.*");
	}
}

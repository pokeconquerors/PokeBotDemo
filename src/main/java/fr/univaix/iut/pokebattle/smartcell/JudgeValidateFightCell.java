package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeValidateFightCell implements SmartCell {
	private JudgeBot owner;

	public JudgeValidateFightCell(JudgeBot owner) {
		this.owner = owner;
	}

	public String getOppenent(String text) {
		String[] tab = text.split(" ");
		return tab[0];
	}

	public String ask(Tweet question) {
		if (!owner.isInFight()) {
			if (question.getText().contains("#fight #ok with")
					&& question.getScreenName() != null) {
				System.out.println("@" + question.getScreenName() + " VS "
						+ getOppenent(question.getText())
						+ " => OK ! Let's ready for the next battle !");
				return "@" + question.getScreenName() + " VS "
						+ getOppenent(question.getText())
						+ " => OK ! Let's ready for the next battle !";
			}
			return null;
		}
		return null;
	}
}

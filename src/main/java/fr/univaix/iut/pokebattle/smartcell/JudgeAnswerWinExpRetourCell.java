package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class JudgeAnswerWinExpRetourCell implements SmartCell {
	private JudgeBot owner;

	public JudgeAnswerWinExpRetourCell(JudgeBot owner) {
		this.owner = owner;
	}

	public String recupXP(String text) {
		String toFind = "#XP=";
		int position = text.indexOf(toFind);
		String tmpXP = text.substring(position + toFind.length());
		System.out.println(tmpXP);
		return tmpXP;
	}

	public String ask(Tweet question) {

		if (question.getText().toLowerCase().contains("#xp=")
				&& question.getScreenName() != null) {
			

			return "@" + question.getScreenName()
					+ " #Win " + "+" + recupXP(question.getText()) + "xp";
		}
		return null;
	}
}

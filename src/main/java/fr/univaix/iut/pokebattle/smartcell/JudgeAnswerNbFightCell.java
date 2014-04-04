package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswerNbFightCell implements SmartCell {
	private JudgeBot owner;
	
	public JudgeAnswerNbFightCell(JudgeBot owner) {
		this.owner = owner;
	}

	public String ask(Tweet question) {
		if (isNotNull(question) && isAFight(question) ) {
				owner.updateDateList(question.getCreatedAt());
				return "@" + question.getScreenName() + " Nombre de combats de la dernière heure : " + owner.getDate5fight().size();
			}	
	
		return null;
	}

	private boolean isNotNull(Tweet question) {
		return question.getScreenName() != null;
	}

	private boolean isAFight(Tweet question) {
		return question.getText().matches(".*\\s+(?i)fight\\s*?.*");
	}
}
package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswerNbFightCell implements SmartCell {
	private JudgeBot owner;
	
	public JudgeAnswerNbFightCell(JudgeBot owner) {
		this.owner = owner;
	}

	public String ask(Tweet question) {
		if ( question.getText().toLowerCase().matches(".*\\s+fight\\s*?.*") && question.getScreenName() != null) {
				if(owner.getDate5fight() == null)return "@" + question.getScreenName() + " Nombre de combats de la dernière heure : 0";
				owner.updateDateList(question.getCreatedAt());
				return "@" + question.getScreenName() + " Nombre de combats de la dernière heure : " + owner.getDate5fight().size();
			}	
	
		return null;
	}
}
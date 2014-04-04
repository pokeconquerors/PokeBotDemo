package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeNbRoundsCell {
	
	private JudgeBot owner;
	
	public JudgeNbRoundsCell(JudgeBot owner) {
		this.owner = owner;
	}
	
	public String ask(Tweet question) {
		if (question.getText().toLowerCase().contains("round ?")) {
			return "Le round en cours est : #" + owner.getNb_Rounds_en_cours();
		}
		else return "Demande incorrecte, la vrai demande est : Round ?";
	}

}

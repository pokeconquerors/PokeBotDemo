package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswerAreneCell implements SmartCell {
	private String arene =null;
	
	public JudgeAnswerAreneCell(String arene) {
		this.arene=arene;
	}

	public String ask(Tweet question) {
		if (question.getText().contains("Gym?")) {
			if (arene != null) {
				arene = " my Gym is " + arene;
			}
			else
			{
				arene = " no Gym";
			}
			System.out.println("@" + question.getScreenName() + arene);
			return "@" + question.getScreenName() + arene;

		}
		return null;
	}
}
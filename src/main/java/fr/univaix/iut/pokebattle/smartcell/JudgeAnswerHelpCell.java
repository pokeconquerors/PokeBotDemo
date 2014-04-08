package fr.univaix.iut.pokebattle.smartcell;

import java.awt.List;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswerHelpCell implements SmartCell {
	private JudgeBot owner = null;
	
	public JudgeAnswerHelpCell (JudgeBot owner) {
		this.owner = owner;
	}
	
	@Override
	public String ask(Tweet question) {
		if(isNotNull(question) && isAskToHelp(question)) {
			return getHelpMessage();
		}
		return null;
	}

	private boolean isAskToHelp(Tweet question) {
		return question.getText().matches(getKeyWord());
	}

	private boolean isNotNull(Tweet question) {
		return question.getScreenName() != null;
	}

	private String getHelpMessage() {
		String tmp = "";
		SmartCell[] smartcells = owner.getSmartCells();
		for (SmartCell smartcell : smartcells) {
			tmp += smartcell.getKeyWord() + " ";
		}
		System.out.println(tmp);
		return tmp;
	}

	@Override
	public String getKeyWord() {
		return ".*\\s+(?i)help.*";
	}

}

package fr.univaix.iut.pokebattle.bot;

import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerAreneCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerCell;

import fr.univaix.iut.pokebattle.smartcell.JudgeNbPLostCell;
import fr.univaix.iut.pokebattle.smartcell.PokemonCriesCell;

import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeBot implements Bot {
	private String arene = null;

	public String getArene() {
		return arene;
	}

	public void setArene(String arene) {
		this.arene = arene;
	}

	/**
	 * List of smartcell the questions go through to find an answer.
	 */
	private final SmartCell[] smartCells = new SmartCell[] {
			new JudgeNbPLostCell(), new JudgeAnswerAreneCell(arene),
			new JudgeAnswerCell() };

	/**
	 * Ask something to Bot, it will respond to you.
	 * 
	 * @param question
	 *            The question you ask.
	 * @return An answer... or null if it doesn't get it.
	 */

	@Override
	public String ask(Tweet question) {
		for (SmartCell cell : smartCells) {
			String answer = cell.ask(question);
			if (answer != null) {
				return answer;
			}
		}
		return null;
	}

}

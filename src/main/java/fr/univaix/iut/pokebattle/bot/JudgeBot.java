package fr.univaix.iut.pokebattle.bot;

import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerAreneCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerCell;
import fr.univaix.iut.pokebattle.smartcell.JudgeNbPLostCell;
import fr.univaix.iut.pokebattle.smartcell.SmartCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeBot implements Bot {
	private JudgeBot instance;
	private String arene = null;
	private long id;	

	
	public JudgeBot() {
	
	}
	
	public JudgeBot(String arene) {
		this.arene = arene;
	}

	public JudgeBot getInstance() {
		return instance;
	}
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * List of smartcell the questions go through to find an answer.
	 */
	private final SmartCell[] smartCells = new SmartCell[] {
			new JudgeNbPLostCell(this), new JudgeAnswerAreneCell(this),
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
	

	public void setArene(String arene) {
		this.arene = arene;		
	}

	public String getArene() {
		return arene;		
	}

}

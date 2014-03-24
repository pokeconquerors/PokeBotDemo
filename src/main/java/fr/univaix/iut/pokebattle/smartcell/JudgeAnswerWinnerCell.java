package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;


/**
 * Reply to all.
 */
public class JudgeAnswerWinnerCell implements SmartCell {
	private JudgeBot owner;
	
	public JudgeAnswerWinnerCell (JudgeBot owner) {
		this.owner = owner;
	}
	
	public int recupGainsXp (String pokemon)
	{
		int Exp;
		float ExpVal = Float.parseFloat(owner.getXP(pokemon));
		float Level =  Float.parseFloat(owner.getLevel(pokemon));
		Exp = (int)(ExpVal * (Level/7));												
		
		return Exp;																
	}
	
	public String ask(Tweet question) {
	
		String WinnerPokemon = owner.getWinnerPokemon(question.getScreenName());
		
		if (question.getText().toLowerCase().contains("#ko") && question.getScreenName() != null && owner.isInFight() ) {
			System.out.println("@" + WinnerPokemon + " #Win +" + recupGainsXp(WinnerPokemon) + "xp");
			
			owner.setInFight(false);
			return "@" + WinnerPokemon + " #Win +" + recupGainsXp(WinnerPokemon) + "xp";
		}
		return null;
	}

}

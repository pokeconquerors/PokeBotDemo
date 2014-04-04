package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeNbRoundsCellTest {

	JudgeBot judge = new JudgeBot();
	JudgeNbRoundsCell cell = new JudgeNbRoundsCell(judge);
	
	@Test
	public void testEchec(){
		judge.setInFight(false);
        assertEquals("Demande incorrecte, la vrai demande est : Round ?", cell.ask(new Tweet("nedseb", "Round?")));
    }
	
	@Test
	public void testRound0(){
		judge.setInFight(false);
        assertEquals("Le round en cours est : #0", cell.ask(new Tweet("nedseb", "Round ?")));
    }
	
	
	@Test
	public void testRound1() {
		judge.setInFight(false);
		judge.IncrNb_Rounds_en_cours();
        assertEquals("Le round en cours est : #1", cell.ask(new Tweet("nedseb", "Round ?")));
    }
	
	
}

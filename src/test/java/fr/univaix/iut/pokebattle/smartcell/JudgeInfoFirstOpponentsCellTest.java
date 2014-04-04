package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeInfoFirstOpponentsCellTest {
	
	JudgeBot judge = new JudgeBot();
	JudgeInfoFirstOpponentsCell cell = new JudgeInfoFirstOpponentsCell(judge);
	
	
	@Test
	public void test() {
		assertEquals("Pokemon = @bulbizare1, Proprio = @pcreux", cell.ask(new Tweet("pcreux", "@nedseb #fight with @bulbizare1 /cc @viviane")));
	}

}

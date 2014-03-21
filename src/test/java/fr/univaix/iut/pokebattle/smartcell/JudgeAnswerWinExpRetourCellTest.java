package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswerWinExpRetourCellTest {

	JudgeAnswerWinExpRetourCell cell = new JudgeAnswerWinExpRetourCell(new JudgeBot());
	@Test
	public void test_noNameXP() {
		assertEquals("@Bulbizare #Win +10xp", cell.ask(new Tweet("Bulbizare", "@TwitterTest #XP=10")));
	}

}

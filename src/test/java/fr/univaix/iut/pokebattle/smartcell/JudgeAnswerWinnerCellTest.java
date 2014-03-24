package fr.univaix.iut.pokebattle.smartcell;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerWinnerCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswerWinnerCellTest {
	JudgeBot judge = new JudgeBot();
	JudgeAnswerWinnerCell cell = new JudgeAnswerWinnerCell(judge);
	
	@Test
	public void test_NoOwner() {
		assertThat(judge.ask(new Tweet("Salut !"))).isNull();
	}
	
	@Test
	public void test_XPWin() {
		judge.pushPokemon("Carapuce", "twitterTest1", "1", "70");
		judge.pushPokemon("Bulbizare", "twitterTest2", "1", "70");
		judge.setInFight(true);
		assertEquals("@Bulbizare #Win +10xp", cell.ask(new Tweet("Carapuce", "#KO /cc @twitterJudge @nedseb @pcreux")));
	}
	

}

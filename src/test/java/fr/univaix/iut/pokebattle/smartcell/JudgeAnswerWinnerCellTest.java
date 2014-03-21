package fr.univaix.iut.pokebattle.smartcell;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswerWinnerCellTest {
	JudgeBot judge = new JudgeBot();
	
	@Test
	public void test_NoOwner() {
		assertThat(judge.ask(new Tweet("Salut !"))).isNull();
	}
	
	@Test
	public void testWinner() {
		judge.pushPokemon("Carapuce", "twitterTest1");
		judge.pushPokemon("Bulbizard", "twitterTest2");
		JudgeAnswerWinnerCell cell = new JudgeAnswerWinnerCell(judge);
		assertEquals("@Bulbizard #Win", cell.ask(new Tweet("Carapuce", "#KO /cc @twitterJudge @nedseb @pcreux")));
		
	}
	

}

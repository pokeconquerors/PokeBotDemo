package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersHelpCellTest {
	JudgeAnswersHelpCell cell = null;

	@Before
	public void setUp () {
		JudgeBot judge = new JudgeBot();
		cell = new JudgeAnswersHelpCell(judge);
	}
		
	@Test
	public void test_Exists() {
		assertTrue(cell != null);
	}
	
	@Test
	public void test_NeedHelp() {
		assertTrue(cell.ask(new Tweet("TwitterTest", "@PokeConquerors Help me !")) != null);
	}
	
	@Test
	public void test_Retour() {
		String chaine = ".*(?i)overbid\\s+.* .*(?i)#fight with .* null .*(?i)round\\s*\\?.* #(?i)ko.* "
				+ ".*\\s+(?i)fight\\s*?.* .*\\s+(?i)hire\\s*!.* .*\\s+#fight #ok with\\s+.* "
				+ "@.+ #(?i)attack #.+ @.+.* .*\\s+(?i)gym\\s*\\?.* .*\\s+(?i)salut(!.*|\\s+.*|) .*\\s+(?i)help.* ";
		assertEquals(chaine, cell.ask(new Tweet("TwitterTest", "@PokeConquerors Help")));
	}

}

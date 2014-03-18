package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswerCellTest {
	
	JudgeAnswerCell cell = new JudgeAnswerCell();
	
	@Test
	public void testAsk_Epeditor() {
        assertEquals("@Boulet Salisalut très cher voisin !", cell.ask(new Tweet("Boulet","@PokeConquerors Salut!")));
	}
}

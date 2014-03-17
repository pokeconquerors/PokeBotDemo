package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;
import fr.univaix.iut.pokebattle.smartcell.JudgeNbPLostCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;
import org.junit.Test;

public class JudgeNbPLostCellTest {	
	JudgeNbPLostCell cell = new JudgeNbPLostCell();
	
	@Test
	public void test_NbPLost_NomPokemon() {
		assertEquals("@NomPokemon -10 pv /cc @TwitterTest", cell.ask(new Tweet("TwitterTest", "@NomPokemon /cc @TwitterTest")));
	}
	
	@Test
	public void test_NbPLost_Carapuce() {
		assertEquals("@Carapuce -10 pv /cc @TwitterTest", cell.ask(new Tweet("TwitterTest", "@Carapuce /cc @TwitterTest")));
	}
}

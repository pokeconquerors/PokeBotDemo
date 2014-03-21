package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.smartcell.JudgeNbPLostCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class JudgeNbPLostCellTest {	
	JudgeNbPLostCell cell = new JudgeNbPLostCell(new JudgeBot());
	
	@Test
	public void test_NoOwner() {
		assertThat(cell.ask(new Tweet("Salut !"))).isNull();
	}
	
	@Test
	public void test_NbPLost_NomPokemon() {
		assertEquals("@NomPokemon -10 pv /cc @TwitterTest", cell.ask(new Tweet("TwitterTest", "@NomPokemon #attack /cc @TwitterTest")));
	}
	
	@Test
	public void test_NbPLost_Carapuce() {
		assertEquals("@Carapuce -10 pv /cc @TwitterTest", cell.ask(new Tweet("TwitterTest", "@Carapuce #attack /cc @TwitterTest")));
	}
}

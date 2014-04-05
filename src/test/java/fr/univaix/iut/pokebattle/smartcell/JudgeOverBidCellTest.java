package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeOverBidCellTest {
	JudgeOverBidCell cell = null;
	JudgeBot judge = null;
	
	@Before
	public void setUp() {
		judge = new JudgeBot();
		cell = new JudgeOverBidCell(judge);
	}
	
	@Test
	public void test_noOwner() {
		assertTrue(cell.ask(new Tweet("")) == null);
	}
	
	@Test
	public void test_ForThisCell() {
		assertTrue(cell.ask(new Tweet("TwitterMyTest", "@PokeConquerors Overbid a")) != null);
	}
	
	@Test
	public void test_TooMuchOverBid() {
		assertEquals("@TwitterMyTest Sale Radin ! Retourne à l'âge de Pierre", cell.ask(new Tweet("TwitterMyTest", "@PokeConquerors Overbid 10000 €")));
	}

	@Test
	public void test_InterestedByOverBid() {
		assertEquals("@TwitterMyTest Nous allons bien nous entendre ! my gym is @TwitterMyTest", cell.ask(new Tweet("TwitterMyTest", "@PokeConquerors Overbid 500 €")));
	}
	
	@Test
	public void test_Hiring() {
		cell.ask(new Tweet("TwitterMyTest", "@PokeConquerors Overbid 500 €"));
		assertEquals("@TwitterMyTest", judge.getArene());
	}
	
	@Test
	public void test_Salaire() {
		cell.ask(new Tweet("TwitterMyTest", "@PokeConquerors Overbid 500 €"));
		assertEquals(500, judge.getSalaire());	
	}
	
	@Test
	public void test_OverOverBid() {
		cell.ask(new Tweet("RischTwitter", "@PokeConquerors Overbid 900 €"));
		assertEquals("@RischTwitter me paie 900", judge.getArene() +" me paie " + judge.getSalaire());
	}
}

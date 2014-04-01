package fr.univaix.iut.pokebattle.smartcell;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAlwaysAnswersCellTest {

	JudgeAlwaysAnswersCell cell = null;
	
	@Before
	public void setUp() {
		cell = new JudgeAlwaysAnswersCell();
	}
	
	@Test
	public void test_IsNotNull() {
		assertThat(cell.ask(new Tweet(""))).isNotNull();
	}
	
	@Test
	public void test_Inconnu() {
		assertEquals("Un pokemon sauvage apparait", cell.ask(new Tweet("")));
	}
	
	@Test
	public void test_Connu() {
		assertEquals("@Sacha Bienvenue dans le monde fascinant des pokémons", cell.ask(new Tweet("Sacha", "")));
	}

}

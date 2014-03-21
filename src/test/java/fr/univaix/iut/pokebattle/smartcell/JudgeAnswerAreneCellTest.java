package fr.univaix.iut.pokebattle.smartcell;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswerAreneCellTest {
		
	@Test
	public void Test_JudgeAnswerArene_Viridian() {
		JudgeAnswerAreneCell cell = new JudgeAnswerAreneCell(new JudgeBot("ViridianGym"));
		assertEquals("@TwitterTest my Gym is ViridianGym", cell.ask(new Tweet("TwitterTest", "@PokeConquerors Gym?")));		
	}
	
	@Test
	public void Test_JudgeAnswerArene_No() {
		JudgeAnswerAreneCell cell = new JudgeAnswerAreneCell(new JudgeBot());
		assertEquals("@TwitterTest no Gym", cell.ask(new Tweet("TwitterTest", "@PokeConquerors Gym?")));		
	}
	
	@Test
	public void Test_JudgeAnswerArene_Null() {
		JudgeAnswerAreneCell cell = new JudgeAnswerAreneCell(new JudgeBot());
		assertThat(cell.ask(new Tweet("TwitterTest", "@Gym?"))).isNull();		
	}
}

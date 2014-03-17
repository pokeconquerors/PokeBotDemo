package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswerAreneCellTest {
	JudgeAnswerAreneCell cell = new JudgeAnswerAreneCell("ViridianGym");
	
	@Test
	public void Test_JudgeAnswerArene_Viridian() {
		
		assertEquals("@TwitterTest my Gym is ViridianGym", cell.ask(new Tweet("TwitterTest", "Gym?")));
		
	}
}

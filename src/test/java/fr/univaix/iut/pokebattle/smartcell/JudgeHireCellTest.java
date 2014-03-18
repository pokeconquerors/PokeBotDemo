package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeHireCellTest {

	@Test
	public void Test_Hire_possible() {
		JudgeHireCell cell = new JudgeHireCell(new JudgeBot());
		assertEquals("@titi my gym is @titi",
				cell.ask(new Tweet("titi", "Hire!")));
	}

	@Test
	public void Test_Hire_impossible() {
		JudgeHireCell cell = new JudgeHireCell(new JudgeBot());
		assertEquals("@toto my gym is @toto",
				cell.ask(new Tweet("toto", "Hire!")));
		assertEquals("@titi @toto is my owner",
				cell.ask(new Tweet("titi", "Hire!")));
	}

}

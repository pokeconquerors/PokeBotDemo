package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeHireCellTest {
	private static JudgeBot judge = null;
	
	@BeforeClass
	public static void setUp () {
		TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
		judge = new JudgeBot();
		judge.setTwitter(twitter);
	}
	
	@Test
	public void Test_Hire_possible() {
		JudgeHireCell cell = new JudgeHireCell(judge);
		judge.setArene(null);
		assertEquals("@titi my gym is @titi",
				cell.ask(new Tweet("titi", " Hire!")));
	}

	@Test
	public void Test_Hire_impossible() {
		JudgeHireCell cell = new JudgeHireCell(judge);
		cell.ask(new Tweet("toto", " Hire!"));
		assertEquals("@titi @toto is my owner but maybe ...", cell.ask(new Tweet("titi", " Hire!")));
	}
	
	@Test
	public void test_Hire_ExceptionUpdate () {
		JudgeHireCell cell = new JudgeHireCell(new JudgeBot());
		cell.ask(new Tweet("toto", " Hire!"));		
	}

}

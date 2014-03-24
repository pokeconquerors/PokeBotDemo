package fr.univaix.iut.pokebattle.smartcell;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.tuse.Credentials;
import fr.univaix.iut.pokebattle.twitter.Tweet;
import fr.univaix.iut.pokebattle.twitter.TwitterBuilder;

public class JudgeAnswerAreneCellTest {
		
	@Test
	public void Test_JudgeAnswerArene_Viridian() {
		JudgeAnswerAreneCell cell = new JudgeAnswerAreneCell(new JudgeBot("ViridianGym"));
		assertEquals("@TwitterTest my Gym is ViridianGym", cell.ask(new Tweet("TwitterTest", "@PokeConquerors Gym?")));		
	}
	
	@Test
	public void Test_JudgeAnswerArene_No() {
		TwitterFactory factory = new TwitterFactory();
		Twitter twitter = factory.getInstance();
		JudgeBot  juge = new JudgeBot();
		juge.setTwitter(twitter);
		JudgeAnswerAreneCell cell = new JudgeAnswerAreneCell(juge);
		assertEquals("@TwitterTest no Gym", cell.ask(new Tweet("TwitterTest", "@PokeConquerors Gym?")));		
		
	}
	
	@Test
	public void Test_JudgeAnswerArene_Null() {
		JudgeAnswerAreneCell cell = new JudgeAnswerAreneCell(new JudgeBot());
		assertThat(cell.ask(new Tweet("TwitterTest", "@Gym?"))).isNull();		
	}
}

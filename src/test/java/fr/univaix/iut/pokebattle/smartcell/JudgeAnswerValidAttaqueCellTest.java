package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;
import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerValidAttaqueCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class JudgeAnswerValidAttaqueCellTest {
	JudgeBot					judge	= new JudgeBot();
	JudgeAnswerValidAttaqueCell	cell	= new JudgeAnswerValidAttaqueCell(judge);

	@Test
	public void test_NoOwner() {
		assertThat(cell.ask(new Tweet("Salut !"))).isNull();
	}

	@Test
	public void test_PokemonOK() {
		judge.pushPokemon("@Carapuce", "twitterTest1", "1", "70");
		judge.pushPokemon("@Bulbizarre", "twitterTest2", "1", "70");
		assertEquals("@Bulbizarre -10pv /cc @twitterTest2 #1", cell.ask(new Tweet("Carapuce",
				"@Bulbizarre #attack #charge /cc @twitterTest2 @twitterTest1 @pokeconquerors #1")));
	}

	@Test
	public void test_attaque() {
		assertEquals("#charge", cell.getElementInArray(
				"@NomPokemon #attack #charge @NomAdversaire /cc @TwitterTest @Judge", 2));

	}

	@Test
	public void test_attaque_inconnue() {
		judge.pushPokemon("@Carapuce", "twitterTest1", "1", "70");
		judge.pushPokemon("@Bulbizarre", "twitterTest2", "1", "70");
		assertEquals("@Carapuce -0pv /cc @twitterTest1 #1 Tu ne respectes pas la convention d'attaque, tu passes le prochain tour",
				cell.ask(new Tweet("Bulbizarre",
						"@Carapuce #attack #AttInconnue /cc @TwitterTest2 @twitterTest1 @pokeconquerors #1")));
	}

	@Test
	public void test_KeyWord() {
		assertEquals("@.+ #(?i)attack #.+ @.+.*", cell.getKeyWord());
	}

	@Test
	public void aaa() {
		judge.pushPokemon("@Carapuce", "twitterTest1", "1", "70");
		judge.pushPokemon("@Bulbizarre", "twitterTest2", "1", "70");
		assertEquals(
				"@Bulbizarre -0pv /cc @twitterTest2 #1 Tu ne respectes pas la convention d'attaque, tu passes le prochain tour",
				cell.ask(new Tweet("Carapuce",
						"@Bulbizarre #attack #charge /cc @twitterTest2 @twitterTest1 @pokeconquerors #2")));
	}

}

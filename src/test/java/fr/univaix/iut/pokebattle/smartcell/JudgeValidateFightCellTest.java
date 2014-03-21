package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;
import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.smartcell.JudgeValidateFightCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class JudgeValidateFightCellTest {
	
	JudgeBot judge = new JudgeBot();
	JudgeValidateFightCell cell = new JudgeValidateFightCell(judge);
	
	@Test
	public void testSalut() {
        assertEquals("@nedseb VS @pcreux => OK ! Let's ready for the next battle !", cell.ask(new Tweet("nedseb", "@pcreux #fight #ok with @pikachuNyanNian /cc @viviane")));
    }
	
	@Test
	public void testSalut1() {
		judge.setInFight(true);
		assertEquals("Je suis déjà en combat, veuillez me contacter plus tard jeune dresseur bipéde, cordialement", cell.ask(new Tweet("nedseb", "@pcreux #fight #ok with @pikachuNyanNian /cc @viviane")));
    }
	
	@Test
	public void test_NoOwner() {
		assertThat(cell.ask(new Tweet("Salut !"))).isNull();
	}
}

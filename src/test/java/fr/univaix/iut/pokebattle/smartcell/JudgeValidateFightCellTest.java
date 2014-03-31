package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.smartcell.JudgeValidateFightCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class JudgeValidateFightCellTest {
	
	JudgeBot judge = new JudgeBot();
	JudgeValidateFightCell cell = new JudgeValidateFightCell(judge);
	JudgeAnswerNbFightCell cell2 = new JudgeAnswerNbFightCell(judge);
	
	@Test
	public void testSalut() {
		judge.setInFight(false);
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
	@Test
	public void testNb_Fight_toomany(){
		List<Date> l = Arrays.asList(new Date(4448),new Date(65595),new Date(5448),new Date(6659),new Date(959));
		judge.setDate5fight(l);
    	Tweet t =new Tweet("Boulet","@PokeConquerors #fight #ok with f");
    	t.setCreatedAt(new Date(33333));
		assertEquals("@Boulet Conseil du prof chen : pas plus de 5 combat en une heure !", cell.ask(t));
	}
}

package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;


import java.util.Date;

import org.junit.Test;


import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswerNbFightCellTest {
	JudgeBot judge = new JudgeBot();
	JudgeAnswerNbFightCell cell = new JudgeAnswerNbFightCell(judge);
	JudgeValidateFightCell cell2 = new JudgeValidateFightCell(judge);
	
	@Test
	public void test_regex () {
		Tweet t =new Tweet("Boulet","@PokeConquerors fight?");
    	t.setCreatedAt(new Date(System.currentTimeMillis()));
        assertEquals("@Boulet Nombre de combats de la dernière heure : 0", cell.ask(t));
	}
	
	@Test
	public void test_regex_2 () {
		Tweet t =new Tweet("Boulet","@PokeConquerors fight  ?");
    	t.setCreatedAt(new Date(System.currentTimeMillis()));
        assertEquals("@Boulet Nombre de combats de la dernière heure : 0", cell.ask(t));
	}
	
	@Test
	public void test_regex_WrongChar() {
		Tweet t =new Tweet("Boulet","@PokeConquerors fighta?");
    	t.setCreatedAt(new Date(System.currentTimeMillis()));
        assertEquals("@Boulet Nombre de combats de la dernière heure : 0", cell.ask(t));
	}
	
	@Test
	public void testNb_Fights_0() {
		Tweet t =new Tweet("Boulet","@PokeConquerors fight ?");
    	t.setCreatedAt(new Date(System.currentTimeMillis()));
        assertEquals("@Boulet Nombre de combats de la dernière heure : 0", cell.ask(t));
	}
	@Test
	public void testNb_Fights_1() {
		Tweet t =new Tweet("Boulet","@PokeConquerors fight ?");
    	t.setCreatedAt(new Date(System.currentTimeMillis()));
    	Tweet t1 = new Tweet("coucou","@pcreux #fight #ok with @pikachu /cc @viviane");
    	t1.setCreatedAt(new Date (System.currentTimeMillis()));
    	judge.pushPokemon("@Bulbizarre", "@nedseb", null, null);;
    	cell2.ask(t1);
		
        assertEquals("@Boulet Nombre de combats de la dernière heure : 1", cell.ask(t));
	}
	@Test
	public void testNb_Fight_1bis(){
		Tweet t1 = new Tweet("coucou","@pcreux #fight #ok with @pikachu /cc @viviane");
    	t1.setCreatedAt(new Date (0));
    	judge.pushPokemon("@Bulbizarre", "@nedseb", null, null);;
    	cell2.ask(t1);
    	judge.setInFight(false);
    	Tweet t11 = new Tweet("coucou","@pcreux #fight #ok with @pikachu /cc @viviane");
    	t11.setCreatedAt(new Date (System.currentTimeMillis()));
    	cell2.ask(t11);
		judge.setInFight(false);
		Tweet t =new Tweet("Boulet","@PokeConquerors fight ?");
    	t.setCreatedAt(new Date(System.currentTimeMillis()));
		assertEquals("@Boulet Nombre de combats de la dernière heure : 1", cell.ask(t));
	}
}

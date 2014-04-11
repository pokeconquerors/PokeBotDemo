package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeGetFightWithCellTest {

    JudgeBot                    judge = new JudgeBot();
    JudgeGetFightWithCell cell  = new JudgeGetFightWithCell(judge);

    @Test
    public void test() {
        cell.ask(new Tweet("pcreux", "@nedseb #fight with @bulbizare1 /cc @viviane"));
        assertEquals("@bulbizare1", judge.getPokemonFromList("bulbizare1"));
    }

    @Test
    public void test_KeyWord() {
        assertEquals(".*(?i)#fight with .*", cell.getKeyWord());
    }

}

package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswersStartFightCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class JudgeAnswersStartFightCellTest {

    JudgeBot                   judge = new JudgeBot();
    JudgeGetFightWithCell      cell  = new JudgeGetFightWithCell(judge);
    JudgeAnswersStartFightCell cell2 = new JudgeAnswersStartFightCell(judge);

    @Test
    public void testSalut() {
        judge.setInFight(false);
        cell.ask(new Tweet("pcreux", "@nedseb #fight with @bulbizare1 /cc @viviane"));
        assertEquals("Round #1 /cc @pcreux @bulbizare1 @nedseb @pikachuNyanNian",
                cell2.ask(new Tweet("nedseb",
                        "@pcreux #fight #ok with @pikachuNyanNian /cc @viviane")));
    }

    @Test
    public void testSalut1() {
        judge.setInFight(true);
        assertEquals("Je suis déjà en combat, veuillez me "
                + "contacter plus tard jeune dresseur bipéde," + " cordialement",
                cell2.ask(new Tweet("nedseb",
                        "@pcreux #fight #ok with @pikachuNyanNian /cc @viviane")));
    }

    @Test
    public void test_NoOwner() {
        assertThat(cell.ask(new Tweet("Salut !"))).isNull();
    }

    @Test
    public void testNb_Fight_toomany() {
        List<Date> l = Arrays.asList(new Date(4448), new Date(65595), new Date(5448),
                new Date(6659), new Date(959));
        judge.setDate5fight(l);
        Tweet t = new Tweet("Boulet", "@PokeConquerors #fight #ok with f");
        t.setCreatedAt(new Date(33333));
        assertEquals("@Boulet Conseil du prof chen : pas plus de 5 combat en une heure !",
                cell2.ask(t));
    }

    @Test
    public void test_KeyWord() {
        assertEquals(".*(?i)#fight with .*", cell.getKeyWord());
    }
}

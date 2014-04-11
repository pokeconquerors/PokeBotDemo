package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersNumRoundCellTest {

    JudgeBot          judge = new JudgeBot();
    JudgeAnswersNumRoundCell cell  = new JudgeAnswersNumRoundCell(judge);

    @Test
    public void testEchec() {
        judge.setInFight(false);
        assertEquals(null, cell.ask(new Tweet("nedseb", "Round")));
    }

    @Test
    public void testRound0() {
        judge.setInFight(false);
        assertEquals("Le round en cours est : #0",
                cell.ask(new Tweet("nedseb", "Round ?")));
    }

    @Test
    public void testRound1() {
        judge.setInFight(false);
        judge.incrIdRoundsEnCours();
        assertEquals("Le round en cours est : #1",
                cell.ask(new Tweet("nedseb", "Round ?")));
    }
    
    @Test
    public void test_KeyWord() {
        assertEquals(".*(?i)round\\s*\\?.*", cell.getKeyWord());
    }

}

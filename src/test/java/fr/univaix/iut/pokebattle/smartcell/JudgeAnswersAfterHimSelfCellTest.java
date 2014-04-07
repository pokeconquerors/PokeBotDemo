package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersAfterHimSelfCellTest {
    JudgeAnswersAfterHimSelfCell cell  = null;
    JudgeBot                     judge = null;

    @Before
    public void setUp() {
        judge = new JudgeBot();
        cell = new JudgeAnswersAfterHimSelfCell(judge);
        judge.setScreenName("PokeConquerors");
        judge.incrNbRoundsEnCours();
        judge.pushPokemon("@bulbizarre", "@nedseb", null, null);
    }

    @Test
    public void test_1stPokemon() {
        judge.pushPokemon("@bulbizarbi", "@nebsed", null, null);
        assertEquals("Round #1 /cc @nedseb @bulbizarre @nebsed @bulbizarbi",
                cell.ask(new Tweet("PokeConquerors",
                        "@bulbizarre -10pc /cc @nedseb #1")));
    }

    @Test
    public void test_2ndPokemon() {
        judge.pushPokemon("@bulbizarbi", "@nebsed", null, null);
        assertEquals("Round #1 /cc @nedseb @bulbizarre @nebsed @bulbizarbi",
                cell.ask(new Tweet("PokeConquerors",
                        "@bulbizarbi -10pc /cc @nebsed #1")));
    }

    @Test
    public void test_null() {
        judge.pushPokemon("@bulbizarbi", "@nebsed", null, null);
        assertEquals(null, cell.ask(new Tweet("PokeConquerors",
                "@bulbiVraimentZarbi -10pc /cc @nedseb #1")));
    }

    @Test
    public void test_1pokemon() {
        JudgeBot judgeBot = new JudgeBot();
        JudgeAnswersAfterHimSelfCell HimSelfcell = new JudgeAnswersAfterHimSelfCell(
                judgeBot);
        judgeBot.setScreenName("PokeConquerors");
        judgeBot.incrNbRoundsEnCours();
        judgeBot.pushPokemon("@bulbizarre", "@nedseb", null, null);
        assertEquals(null, HimSelfcell.ask(new Tweet("PokeConquerors",
                "@bulbiVraimentZarbi -10pc /cc @nedseb #1")));
    }

    @Test
    public void test_Round1() {
        JudgeBot judgeBot = new JudgeBot();
        JudgeAnswersAfterHimSelfCell HimSelfcell = new JudgeAnswersAfterHimSelfCell(
                judgeBot);
        judgeBot.setScreenName("PokeConquerors");
        judgeBot.incrNbRoundsEnCours();
        judgeBot.pushPokemon("@bulbizarre", "@nedseb", null, null);
        judgeBot.pushPokemon("@bulbizarbi", "@nebsed", null, null);
        assertEquals(null, HimSelfcell.ask(new Tweet("PokeConquerors",
                "Round #1 /cc @nebsed @bulbizarbi @nedseb @bulbizarre")));
    }
}

package fr.univaix.iut.pokebattle.smartcell;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersAlwaysCellTest {

    JudgeAnswersAlwaysCell cell = null;

    @Before
    public void setUp() {
        cell = new JudgeAnswersAlwaysCell(new JudgeBot());
    }

    @Test
    public void test_IsNotNull() {
        assertThat(cell.ask(new Tweet(""))).isNotNull();
    }

    @Test
    public void test_Inconnu() {
        assertEquals("Un pokemon sauvage apparait", cell.ask(new Tweet("")));
    }

    @Test
    public void test_Connu() {
        assertEquals("@Sacha Bienvenue dans le monde fascinant des pokémons",
                cell.ask(new Tweet("Sacha", "")));
    }

    @Test
    public void test_KeyWord() {
        assertEquals("<no Keyword>", cell.getKeyWord());
    }

    @Test
    public void test_null() {
        JudgeBot judge = new JudgeBot();
        cell = new JudgeAnswersAlwaysCell(judge);
        judge.setWait(true);
        assertEquals(null, cell.ask(new Tweet("")));
    }

}

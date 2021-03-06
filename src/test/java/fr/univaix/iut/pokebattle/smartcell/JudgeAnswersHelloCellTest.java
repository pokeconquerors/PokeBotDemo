package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersHelloCellTest {

    JudgeAnswersHelloCell cell = new JudgeAnswersHelloCell();

    @Test
    public void testAsk_Epeditor() {
        assertEquals("@Boulet Salisalut très cher voisin !",
                cell.ask(new Tweet("Boulet", "@PokeConquerors Salut!")));
    }

    @Test
    public void testAsk_regex_OneWhiteSpace() {
        assertEquals("@Boulet2 Salisalut très cher voisin !",
                cell.ask(new Tweet("Boulet2", "@PokeConquerors Salut !")));
    }

    @Test
    public void testAsk_regex_TwohiteSpace() {
        assertEquals("@Boulet Salisalut très cher voisin !",
                cell.ask(new Tweet("Boulet", "@PokeConquerors Salut  !")));
    }

    @Test
    public void testAsk_regex_Wrong() {
        assertThat(cell.ask(new Tweet("Boulet", "@PokeConquerors SalutSalut!"))).isNull();
    }

    @Test
    public void testAsk_regex_Wrong_2() {
        assertThat(cell.ask(new Tweet("Boulet", "@PokeConquerors Saluta!"))).isNull();
    }

    @Test
    public void testAsk_regex_Wrong_noexclamation() {
        assertThat(cell.ask(new Tweet("Boulet", "@PokeConquerors Salut"))).isNotNull();
    }

    @Test
    public void testAsk_regex_Wrong_InterroExclamation() {
        assertThat(cell.ask(new Tweet("Boulet", "@PokeConquerors Salut?!"))).isNull();
    }

    @Test
    public void test_KeyWord() {
        assertEquals(".*\\s+(?i)salut(!.*|\\s+.*|)", cell.getKeyWord());
    }
}

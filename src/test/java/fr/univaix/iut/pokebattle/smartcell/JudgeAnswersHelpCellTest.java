package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersHelpCellTest {
    JudgeAnswersHelpCell cell = null;

    @Before
    public void setUp() {
        JudgeBot judge = new JudgeBot();
        cell = new JudgeAnswersHelpCell(judge);
    }

    @Test
    public void test_Exists() {
        assertTrue(cell != null);
    }

    @Test
    public void test_NeedHelp() {
        assertTrue(cell.ask(new Tweet("TwitterTest", "@PokeConquerors Help me !")) != null);
    }

    @Test
    public void test_Retour() {
        String chaine = "<Overbid> <#fight with> <round ?> <#ko> <fight ?> <hire !> <#fight #ok with> <#attack #ton_Attaque> <gym ?> <salut !> <help> <...>";
        assertEquals("@TwitterTest " + chaine, cell.ask(new Tweet("TwitterTest", "@PokeConquerors Help")));
    }

}

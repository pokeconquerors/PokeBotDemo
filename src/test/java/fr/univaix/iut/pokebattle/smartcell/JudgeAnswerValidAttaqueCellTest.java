package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;
import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswerValidAttaqueCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class JudgeAnswerValidAttaqueCellTest {
    JudgeBot                    judge = new JudgeBot();
    JudgeAnswerValidAttaqueCell cell  = new JudgeAnswerValidAttaqueCell(judge);

    @Test
    public void test_NoOwner() {
        assertThat(cell.ask(new Tweet("Salut !"))).isNull();
    }

    @Test
    public void test_regex() {
        judge.pushPokemon("Carapuce", "twitterTest1", "1", "70");
        judge.pushPokemon("Bulbizarre", "twitterTest2", "1", "70");
        assertEquals(
                "@Bulbizarre -10pv /cc @twitterTest1 #1",
                cell.ask(new Tweet("twitterTest1",
                        "@Carapuce #attack #charge @Bulbizarre /cc @twitterTest1 @pokeconquerors")));
    }

    @Test
    public void test_PokemonOK() {
        judge.pushPokemon("Carapuce", "twitterTest1", "1", "70");
        judge.pushPokemon("Bulbizarre", "twitterTest2", "1", "70");
        assertEquals(
                "@Bulbizarre -10pv /cc @twitterTest1 #1",
                cell.ask(new Tweet("twitterTest1",
                        "@Carapuce #attack #charge @Bulbizarre /cc @twitterTest1 @pokeconquerors")));
    }

    @Test
    public void test_attaque() {
        assertEquals(
                "#charge",
                cell.getElementInArray(
                        "@NomPokemon #attack #charge @NomAdversaire /cc @TwitterTest @Judge",
                        2));

    }

    @Test
    public void test_attaque_inconnue() {
        judge.pushPokemon("Carapuce", "twitterTest1", "1", "70");
        judge.pushPokemon("Bulbizarre", "twitterTest2", "1", "70");
        assertEquals(
                "@Bulbizarre -0pv /cc @twitterTest1 #1",
                cell.ask(new Tweet("twitterTest1",
                        "@Carapuce #attack #AttInconnue @Bulbizarre /cc @twitterTest1 @pokeconquerors")));
    }

}

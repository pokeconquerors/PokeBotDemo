package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.*;
import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.smartcell.JudgeAnswersValidAttaqueCell;
import fr.univaix.iut.pokebattle.twitter.Tweet;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class JudgeAnswersValidAttaqueCellTest {
    JudgeBot                     judge = new JudgeBot();
    JudgeAnswersValidAttaqueCell cell  = new JudgeAnswersValidAttaqueCell(judge);

    @Test
    public void test_NoOwner() {
        assertThat(cell.ask(new Tweet("Salut !"))).isNull();
    }

    @Test
    public void test_PokemonOK() {
        judge.pushPokemon("@Carapuce", "twitterTest1", "1", "70", true);
        judge.pushPokemon("@Bulbizarre", "twitterTest2", "1", "70", true);
        cell.ask(new Tweet("Carapuce",
                "@Bulbizarre #attack #charge /cc @twitterTest2 @twitterTest1 @pokeconquerors #1"));
        assertEquals("@Bulbizarre -10pv /cc @twitterTest2 #1", cell.ask(new Tweet("Bulbizarre",
                "@Carapuce #attack #charge /cc @twitterTest1 @twitterTest2 @pokeconquerors #1")));
    }

    @Test
    public void test_attaque() {
        assertEquals("#charge", cell.getElementInArray(
                "@NomPokemon #attack #charge @NomAdversaire /cc @TwitterTest @Judge", 2));

    }

    @Test
    public void test_attaque_inconnue() {
        judge.pushPokemon("@Carapuce", "twitterTest1", "1", "70", true);
        judge.pushPokemon("@Bulbizarre", "twitterTest2", "1", "70", true);
        cell.ask(new Tweet("Bulbizarre",
                "@Carapuce #attack #AttInconnue /cc @TwitterTest2 @twitterTest1 @pokeconquerors #1"));
        assertEquals(
                "@Carapuce -0pv /cc @twitterTest1 #1 PENALITE : SKIP NEXT ROUND",
                cell.ask(new Tweet("Carapuce",
                        "@Bulbizarre #attack #AttInconnue /cc @TwitterTest1 @twitterTest2 @pokeconquerors #1")));
    }

    @Test
    public void test_KeyWord() {
        assertEquals("@.+ #(?i)attack #.+ @.+.*", cell.getKeyWord());
    }

    @Test
    public void test_Penalite() {
        judge.pushPokemon("@Carapuce", "twitterTest1", "1", "70", true);
        judge.pushPokemon("@Bulbizarre", "twitterTest2", "1", "70", true);
        cell.ask(new Tweet("Carapuce",
                "@Bulbizarre #attack #charge /cc @twitterTest2 @twitterTest1 @pokeconquerors #2"));
        assertEquals(
                "@Bulbizarre -0pv /cc @twitterTest2 #1 PENALITE : SKIP NEXT ROUND",
                cell.ask(new Tweet("Bulbizarre",
                        "@Carapuce #attack #charge /cc @twitterTest1 @twitterTest2 @pokeconquerors #2")));
    }

    @Test
    public void test_o_O() {
        judge.pushPokemon("@Carapuce", "twitterTest1", "1", "70", true);
        judge.pushPokemon("@Bulbizarre", "twitterTest2", "1", "70", true);
        cell.ask(new Tweet("Carapuce",
                "@twitterTest1 o_O ? /cc @twitterTest2 @pokeconquerors @Bulbizarre #1"));
        assertEquals("@Bulbizarre -0pv /cc @twitterTest2 #1", cell.ask(new Tweet("Bulbizarre",
                "@Carapuce #attack #charge /cc @twitterTest1 @twitterTest2 @pokeconquerors #1")));
    }

    @Test
    public void test_nextRound() {
        judge.pushPokemon("@Carapuce", "twitterTest1", "1", "70", false);
        judge.pushPokemon("@Bulbizarre", "twitterTest2", "1", "70", true);
        cell.ask(new Tweet("Carapuce",
                "@Bulbizarre #attack #charge /cc @twitterTest2 @twitterTest1 @pokeconquerors #1"));
        assertEquals("@Bulbizarre -0pv /cc @twitterTest2 #1", cell.ask(new Tweet("Bulbizarre",
                "@Carapuce #attack #charge /cc @twitterTest1 @twitterTest2 @pokeconquerors #1")));
    }

}

package fr.univaix.iut.pokebattle.run;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.univaix.iut.pokebattle.data.DataReadObject;

public class PokemonMainTest {

    @Test
    public void test() {
        PokemonMain.definePokedexFile("pokedex.json");
        assertEquals("pokedex.json", DataReadObject.getInstance()
                .getPokedexFile());
    }

    @Test
    public void test_main() {
        PokemonMain.main(new String[] { "pokedexFile=pokedexMain.json" });
        assertEquals("pokedexMain.json", DataReadObject.getInstance()
                .getPokedexFile());
    }

    @Test
    public void test_main_noArgs() {
        PokemonMain.main(new String[] { "" });
        assertEquals("pokedex.json", DataReadObject.getInstance()
                .getPokedexFile());
    }

    @Test
    public void test_PokemonMain() {
        PokemonMain pokemonMain = new PokemonMain();
        assertTrue(pokemonMain != null);
    }

}

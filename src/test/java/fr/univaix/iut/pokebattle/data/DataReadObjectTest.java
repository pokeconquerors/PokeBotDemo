package fr.univaix.iut.pokebattle.data;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class DataReadObjectTest {

	static DataReadObject dro = null;
	
	@BeforeClass
	public static void setUp() {
		dro = DataReadObject.getInstance();
		dro.setPokedexFile("pokedex.json");
	}
	
	@Test
	public void test_GivePokemons() {
		assertThat(dro.getPokemons()).isNotNull();		
	}
	
	@Test
	public void test_GivePokemon() {
		assertThat(dro.getPokemon(0)).isNotNull();		
	}
	
	
	@Test
	public void test_GiveNom() {
		assertEquals("Bulbizarre", dro.getPokemon(0).nom);		
	}

}

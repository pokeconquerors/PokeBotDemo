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
	public void test_GiveAnOtherPokemon() {
		assertThat(dro.getPokemon(1)).isNotNull();		
	}
	
	@Test
	public void test_GiveIndice_Nom() {
		assertEquals("Bulbizarre", dro.getPokemon(0).nom);		
	}
	
	@Test
	public void test_GiveNom() {
		assertThat(dro.getPokemon("Bulbizarre")).isNotNull();		
	}
	
	@Test
	public void test_GiveWrongNom() {
		assertThat(dro.getPokemon("JeNeSuisPasUnPokemon")).isNull();		
	}
	
	@Test
	public void test_GiveNomAttaque_0() {
		DataObjectPokemon pokemon = dro.getPokemon("bulbizarre");
		assertEquals("Charge", dro.getAttaqueNom(pokemon.attaques[0]));		
	}
	
	@Test
	public void test_GiveNiveauAttaque_0() {
		DataObjectPokemon pokemon = dro.getPokemon("bulbizarre");
		assertEquals("N.7", dro.getAttaqueNiveau(pokemon.attaques[2]));		
	}
	
	@Test
	public void test_GiveNomttaque_0() {
		DataObjectPokemon pokemon = dro.getPokemon("bulbizarre");
		assertEquals("Charge", dro.getAttaqueNom(pokemon.attaques[0]));		
	}
	
	@Test
	public void test_getAttaques_pokemon_exists () {
		DataObjectAttack[] attaques = dro.getAttaques("bulbizarre");
		assertThat(attaques).isNotNull();
	}
	
	@Test
	public void test_getAttaques_pokemon_dont_exist () {
		DataObjectAttack[] attaques = dro.getAttaques("bulbizarbi");
		assertThat(attaques).isNull();
	}
	
	@Test
	public void test_getAttaquePuissance () {
		DataObjectAttack[] attaques = dro.getAttaques("bulbizarre");
		DataObjectAttack attaque = attaques[0];
		assertThat(dro.getAttaquePuissance(attaque)).isNotNull();
		
	}
	
	@Test
	public void test_getAttaquePrecision () {
		DataObjectAttack[] attaques = dro.getAttaques("bulbizarre");
		DataObjectAttack attaque = attaques[0];
		assertThat(dro.getAttaquePrecision(attaque)).isNotNull();
		
	}
	
	@Test
	public void test_getAttaquePP() {
		DataObjectAttack[] attaques = dro.getAttaques("bulbizarre");
		DataObjectAttack attaque = attaques[0];
		assertThat(dro.getAttaquePP(attaque)).isNotNull();
		
	}
	
	@Test
	public void test_getPokedexFile() {
		assertThat(dro.getPokedexFile()).isNotNull();
		
	}
}

package fr.univaix.iut.pokebattle.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class DataReadObject {
	private static DataReadObject instance;
	private DataObjectPokemon[] pokemons;
	private String pokedexFile = "pokedex.json";
	
	public String getPokedexFile () {
		return pokedexFile;
	}
	
	public void setPokedexFile(String pokedexFile) {
		this.pokedexFile = pokedexFile; 
	}
	
	public static DataReadObject getInstance() {
		if(instance == null) {
			instance = new DataReadObject();
		}
		return instance;
	}

	public DataObjectPokemon[] getPokemons() {
		return pokemons;
	}
	
	public DataObjectPokemon getPokemon(int position) {
		return pokemons[position];
	}
	
	public DataObjectPokemon getPokemon(String nomPokemon) {
		for (DataObjectPokemon pokemon : pokemons) {
			if(pokemon.nom.toLowerCase().equals(nomPokemon.toLowerCase()))
				return pokemon;
		}
		return null;
	}


	public void setPokemons(DataObjectPokemon[] pokemons) {
		this.pokemons = pokemons;
	}
	
	private DataReadObject () {
		ChargerJson();
	}
	
	private void ChargerJson() {
		
		Gson gson = new Gson();

        BufferedReader lecteur = new BufferedReader(
                              new InputStreamReader(DataReadObject.class.getClassLoader().getResourceAsStream(pokedexFile)));
        //convert the json string back to object
        setPokemons(gson.fromJson(lecteur, DataObjectPokemon[].class));
    }
	
	public DataObjectAttack[] getAttaques(String nomPokemon) {
		for (DataObjectPokemon pokemon : pokemons) {
			if(pokemon.nom.toLowerCase().equals(nomPokemon.toLowerCase()))
				return pokemon.attaques;
		}
		return null;
	}
	
	public DataObjectAttack getAttaque(String nomPokemon, String nomAttaque) {
		return getAttaque(nomAttaque, getPokemon(nomPokemon));		
	}

	private DataObjectAttack getAttaque(String nomAttaque, DataObjectPokemon pokemon) {
		for (DataObjectAttack attaque : pokemon.attaques) {
			if(attaque.nom.toLowerCase().equals(nomAttaque.toLowerCase()))
				return attaque;
		}
		return null;
	}
	
	public String getAttaqueNom(DataObjectAttack attaque) {
		return attaque.nom;
	}
	
	public String getAttaqueNiveau(DataObjectAttack attaque) {
		return attaque.niveau;
	}
	
	public String getAttaquePuissance(DataObjectAttack attaque) {
		return attaque.puissance;
	}
	
	public String getAttaquePrecision(DataObjectAttack attaque) {
		return attaque.precision;
	}
	
	public String getAttaquePP(DataObjectAttack attaque) {
		return attaque.pp;
	}
}


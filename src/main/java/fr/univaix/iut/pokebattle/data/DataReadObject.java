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


	public void setPokemons(DataObjectPokemon[] pokemons) {
		this.pokemons = pokemons;
	}
	
	private DataReadObject () {
		ChargerJson();
	}
	
	private void ChargerJson() {
		Gson gson = new Gson();

        BufferedReader br = new BufferedReader(
                              new InputStreamReader(DataReadObject.class.getClassLoader().getResourceAsStream(pokedexFile)));
        //convert the json string back to object
        pokemons = gson.fromJson(br, DataObjectPokemon[].class);
	}
}


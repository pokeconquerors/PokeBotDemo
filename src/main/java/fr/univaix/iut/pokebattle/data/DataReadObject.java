package fr.univaix.iut.pokebattle.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public final class DataReadObject {
    private static DataReadObject instance;
    private DataObjectPokemon[]   pokemons;
    private String                pokedexFile = "pokedex.json";

    public String getPokedexFile() {
        return pokedexFile;
    }

    public void setPokedexFile(final String pokedexFile) {
        this.pokedexFile = pokedexFile;
    }

    public static DataReadObject getInstance() {
        if (instance == null) {
            instance = new DataReadObject();
        }
        return instance;
    }

    public DataObjectPokemon[] getPokemons() {
        return pokemons;
    }

    public DataObjectPokemon getPokemon(final int position) {
        return pokemons[position];
    }

    public DataObjectPokemon getPokemon(final String nomPokemon) {
        for (DataObjectPokemon pokemon : pokemons) {
            if (pokemon.nom.toLowerCase().equals(nomPokemon.toLowerCase())) {
                return pokemon;
            }
        }
        return null;
    }

    public void setPokemons(final DataObjectPokemon[] pokemons) {
        this.pokemons = pokemons;
    }

    private DataReadObject() {
        chargerJson();
    }

    private void chargerJson() {

        Gson gson = new Gson();

        BufferedReader lecteur = new BufferedReader(new InputStreamReader(
                DataReadObject.class.getClassLoader().getResourceAsStream(
                        pokedexFile)));
        // convert the json string back to object
        setPokemons(gson.fromJson(lecteur, DataObjectPokemon[].class));
    }

    public DataObjectAttack[] getAttaques(final String nomPokemon) {
        for (DataObjectPokemon pokemon : pokemons) {
            if (pokemon.nom.toLowerCase().equals(nomPokemon.toLowerCase())) {
                return pokemon.attaques;
            }
        }
        return null;
    }

    public DataObjectAttack getAttaque(final String nomPokemon, final String nomAttaque) {
        return getAttaque(nomAttaque, getPokemon(nomPokemon));
    }

    private DataObjectAttack getAttaque(final String nomAttaque,
            final DataObjectPokemon pokemon) {
        for (DataObjectAttack attaque : pokemon.attaques) {
            if (attaque.nom.toLowerCase().equals(nomAttaque.toLowerCase())) {
                return attaque;
            }
        }
        return null;
    }

    public String getAttaqueNom(final DataObjectAttack attaque) {
        return attaque.nom;
    }

    public String getAttaqueNiveau(final DataObjectAttack attaque) {
        return attaque.niveau;
    }

    public String getAttaquePuissance(final DataObjectAttack attaque) {
        return attaque.puissance;
    }

    public String getAttaquePrecision(final DataObjectAttack attaque) {
        return attaque.precision;
    }

    public String getAttaquePP(final DataObjectAttack attaque) {
        return attaque.pp;
    }
}

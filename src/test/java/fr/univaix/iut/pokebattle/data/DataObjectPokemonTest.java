package fr.univaix.iut.pokebattle.data;

import static org.junit.Assert.*;
import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class DataObjectPokemonTest {

    @Test
    public void test_ToString() {
        String nom = "a";
        String espece = "b";
        float taille = 1.0f;
        float poids = 1.0f;
        float fmratio = 1.0f;
        String effortval = "c";
        String type1 = "d";
        String type2 = "e";
        int expval = 1;
        int expmax = 2;
        int captureval = 3;
        String capspe1 = "f";
        String capspe2 = "g";
        String couleur = "h";
        int forme = 4;
        DataObjectAttack[] attaques = null;

        DataObjectPokemon dop = new DataObjectPokemon(nom, espece, taille,
                poids, fmratio, effortval, type1, type2, expval, expmax,
                captureval, capspe1, capspe2, couleur, forme, attaques);

        String pokemon = "DataObjectPokemon{" + "nom='" + nom + '\''
                + ", espece='" + espece + '\'' + ", taille=" + taille
                + ", poids=" + poids + ", fmratio=" + fmratio + ", effortval='"
                + effortval + '\'' + ", type1='" + type1 + '\'' + ", type2='"
                + type2 + '\'' + ", expval=" + expval + ", expmax=" + expmax
                + ", captureval=" + captureval + ", capspe1='" + capspe1 + '\''
                + ", capspe2='" + capspe2 + '\'' + ", couleur='" + couleur
                + '\'' + ", forme=" + forme + ", attaques="
                + (attaques == null ? null : Arrays.asList(attaques)) + '}';

        assertEquals(pokemon, dop.toString());
    }

    @Test
    public void test_VoidConstructor() {
        DataObjectPokemon dop = new DataObjectPokemon();
        assertThat(dop).isNotNull();
    }

}

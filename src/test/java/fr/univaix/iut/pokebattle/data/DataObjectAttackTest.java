package fr.univaix.iut.pokebattle.data;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class DataObjectAttackTest {
	
	@Test
	public void test_ToString () {
		String niveau = "a";
	    String nom = "b";
	    String puissance = "c";
	    String precision = "d";
	    String pp = "e";
		
	    DataObjectAttack doa = new DataObjectAttack(niveau, nom, puissance, precision, pp);
	    
		String attaque = "DataObjectAttack{" +
                "niveau='" + niveau + '\'' +
                ", nom='" + nom + '\'' +
                ", puissance=" + puissance +
                ", precision=" + precision +
                ", pp=" + pp +
                '}';
		
		assertEquals(attaque, doa.toString());
	}

}

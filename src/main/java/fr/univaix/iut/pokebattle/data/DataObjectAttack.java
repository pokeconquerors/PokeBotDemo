package fr.univaix.iut.pokebattle.data;

public class DataObjectAttack {
    String niveau;
    String nom;
    String puissance;
    String precision;
    String pp;

    public DataObjectAttack(final String niveau, final String nom, final String puissance,
            final String precision, final String pp) {
        this.niveau = niveau;
        this.nom = nom;
        this.puissance = puissance;
        this.precision = precision;
        this.pp = pp;
    }

    @Override
    public final String toString() {
        return "DataObjectAttack{" + "niveau='" + niveau + '\'' + ", nom='"
                + nom + '\'' + ", puissance=" + puissance + ", precision="
                + precision + ", pp=" + pp + '}';
    }
}

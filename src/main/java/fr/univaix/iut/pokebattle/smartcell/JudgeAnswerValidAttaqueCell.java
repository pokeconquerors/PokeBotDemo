package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.data.DataObjectAttack;
import fr.univaix.iut.pokebattle.data.DataReadObject;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class JudgeAnswerValidAttaqueCell implements SmartCell {
    private JudgeBot owner;

    public JudgeAnswerValidAttaqueCell(final JudgeBot owner) {
        this.owner = owner;
    }

    DataReadObject dro = DataReadObject.getInstance();

    public final String getElementInArray(final String text, final int indice) {
        String[] tab = text.split(" ");
        return tab[indice];
    }

    public final String ask(final Tweet question) {

        if (isNotNull(question) && isALaunchAttack(question)) {
            String nomPokemon = getPokemon(question);
            owner.incrNbRoundsEnCours();
            if (isACorrectAttack(question)) {
                return "" + owner.getOtherPokemon(nomPokemon) + " -10pv /cc "
                        + "@" + question.getScreenName() + " #"
                        + owner.getNbRoundsencours();
            } else {
                return "" + owner.getOtherPokemon(nomPokemon) + " -0pv /cc "
                        + "@" + question.getScreenName() + " #"
                        + owner.getNbRoundsencours();
            }

        }
        return null;
    }

    private boolean isACorrectAttack(final Tweet question) {
        String nomAttaque = getAttaque(question);
        String nomPokemon = getPokemon(question);
        DataObjectAttack attaque = dro.getAttaque(nomPokemon, nomAttaque);
        return attaque != null;
    }

    private String getPokemon(final Tweet question) {
        return getElementInArray(question.getText(), 0).substring(1);
    }

    private String getAttaque(final Tweet question) {
        return getElementInArray(question.getText(), 2).substring(1);
    }

    private boolean isNotNull(final Tweet question) {
        return question.getScreenName() != null;
    }

    private boolean isALaunchAttack(final Tweet question) {
        return question.getText().matches("@.+ #(?i)attack #.+ @.+.*");
    }

}

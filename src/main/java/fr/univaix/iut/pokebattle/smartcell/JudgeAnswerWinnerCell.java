package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class JudgeAnswerWinnerCell implements SmartCell {
    private static final int POKEMON_COEFFICIENT_XP = 7;
    private JudgeBot owner;

    public JudgeAnswerWinnerCell(final JudgeBot owner) {
        this.owner = owner;
    }

    public final int recupGainsXp(final String pokemon) {
        int exp;
        float expVal = Float.parseFloat(owner.getXPFromList(pokemon));
        float level = Float.parseFloat(owner.getLevelFromList(pokemon));
        exp = (int) (expVal * (level / POKEMON_COEFFICIENT_XP));

        return exp;
    }

    public final String ask(final Tweet question) {

        String winnerPokemon = owner.getOtherPokemon(question.getScreenName());

        if (isNotNull(question) && isDead(question) && owner.isInFight()) {
            owner.setInFight(false);
            owner.reInitIdRoundsEnCours();
            return "@" + winnerPokemon + " #Win +"
                    + recupGainsXp(winnerPokemon) + "xp";
        }
        return null;
    }

    private boolean isNotNull(final Tweet question) {
        return question.getScreenName() != null;
    }

    private boolean isDead(final Tweet question) {
        return question.getText().matches(getKeyWord());
    }

    @Override
    public final String getKeyWord() {
       return "#(?i)ko.*";
    }

}

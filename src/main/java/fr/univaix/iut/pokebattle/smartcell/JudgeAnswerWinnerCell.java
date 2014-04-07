package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class JudgeAnswerWinnerCell implements SmartCell {
    private JudgeBot owner;

    public JudgeAnswerWinnerCell(JudgeBot owner) {
        this.owner = owner;
    }

    public int recupGainsXp(String pokemon) {
        int Exp;
        float ExpVal = Float.parseFloat(owner.getXPFromList(pokemon));
        float Level = Float.parseFloat(owner.getLevelFromList(pokemon));
        Exp = (int) (ExpVal * (Level / 7));

        return Exp;
    }

    public String ask(Tweet question) {

        String WinnerPokemon = owner.getOtherPokemon(question.getScreenName());

        if (isNotNull(question) && isDead(question) && owner.isInFight()) {
            owner.setInFight(false);
            owner.reinitNbRoundsEnCours();
            return "@" + WinnerPokemon + " #Win +"
                    + recupGainsXp(WinnerPokemon) + "xp";
        }
        return null;
    }

    private boolean isNotNull(Tweet question) {
        return question.getScreenName() != null;
    }

    private boolean isDead(Tweet question) {
        return question.getText().matches("#(?i)ko.*");
    }

}

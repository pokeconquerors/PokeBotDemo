package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeNbRoundsCell implements SmartCell {

    private JudgeBot owner;

    public JudgeNbRoundsCell(final JudgeBot owner) {
        this.owner = owner;
    }

    public final String ask(final Tweet question) {
        if (isARoundQuestion(question)) { return "Le round en cours est : #"
                + owner.getNbRoundsencours(); }
        return null;
    }

    private boolean isARoundQuestion(final Tweet question) {
        return question.getText().matches(".*(?i)round\\s*\\?.*");
    }

}

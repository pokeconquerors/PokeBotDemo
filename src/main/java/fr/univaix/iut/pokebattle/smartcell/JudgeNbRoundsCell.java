package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeNbRoundsCell implements SmartCell {

    private JudgeBot owner;

    public JudgeNbRoundsCell(JudgeBot owner) {
        this.owner = owner;
    }

    public String ask(Tweet question) {
        if (isARoundQuestion(question)) { return "Le round en cours est : #"
                + owner.getNbRoundsencours(); }
        return null;
    }

    private boolean isARoundQuestion(Tweet question) {
        return question.getText().matches(".*(?i)round\\s*\\?.*");
    }

}

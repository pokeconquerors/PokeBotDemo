package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAlwaysAnswersCell implements SmartCell {
    public JudgeAlwaysAnswersCell() {
    }

    public String ask(Tweet question) {
        if (isNotNull(question)) { return "@" + question.getScreenName()
                + " Bienvenue dans le monde fascinant des pokémons"; }
        return "Un pokemon sauvage apparait";
    }

    private boolean isNotNull(Tweet question) {
        return question.getScreenName() != null;
    }
}

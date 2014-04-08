package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAlwaysAnswersCell implements SmartCell {
    public JudgeAlwaysAnswersCell() {
    }

    public final String ask(final Tweet question) {
        if (isNotNull(question)) { return "@" + question.getScreenName()
                + " Bienvenue dans le monde fascinant des pokémons"; }
        return "Un pokemon sauvage apparait";
    }

    private boolean isNotNull(final Tweet question) {
        return question.getScreenName() != null;
    }

    @Override
    public final String getKeyWord() {
        return "<no Keyword>";
    }
}

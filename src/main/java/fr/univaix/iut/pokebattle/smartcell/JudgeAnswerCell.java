package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswerCell implements SmartCell {

    public final String ask(final Tweet question) {
        if (isNotNull(question) && isAHello(question)) { return "@"
                + question.getScreenName() + " Salisalut très cher voisin !"; }
        return null;
    }

    private boolean isNotNull(final Tweet question) {
        return question.getScreenName() != null;
    }

    private boolean isAHello(final Tweet question) {
        return (question.getText().matches(".*\\s+(?i)salut!.*")
                || question.getText().matches(".*\\s+(?i)salut\\s+.*")
                || question.getText().matches(getKeyWord()));
    }

    @Override
    public String getKeyWord() {
        return ".*\\s+(?i)salut";
    }
}

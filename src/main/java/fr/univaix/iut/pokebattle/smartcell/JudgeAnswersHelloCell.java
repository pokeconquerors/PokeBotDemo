package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersHelloCell implements SmartCell {

    public final String ask(final Tweet question) {
        if (isNotNull(question) && isAHello(question)) {
            return "@" + question.getScreenName() + " Salisalut très cher voisin !";
        }
        return null;
    }

    private boolean isNotNull(final Tweet question) {
        return question.getScreenName() != null;
    }

    private boolean isAHello(final Tweet question) {
        return (question.getText().matches(getKeyWord()));
    }

    @Override
    public final String getKeyWord() {
        return ".*\\s+(?i)salut(!.*|\\s+.*|)";
    }
}

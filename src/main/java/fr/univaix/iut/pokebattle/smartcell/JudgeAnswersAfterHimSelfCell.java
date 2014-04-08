package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersAfterHimSelfCell implements SmartCell {
    private JudgeBot owner = null;

    public JudgeAnswersAfterHimSelfCell(final JudgeBot owner) {
        this.owner = owner;
    }

    @Override
    public final String ask(final Tweet question) {
        if (isNotNull(question) && isTweetOfMe(question)) {
            String text = question.getText();
            if (owner.isTimeToNextRound(text)) {
                return owner.getCallForNextRound();
            } else if (owner.isTimeToEndRound(text)) {
                return owner.getCallForNextResult();
            }
        }
        return null;
    }

    private boolean isTweetOfMe(final Tweet question) {
        return question.getScreenName().toLowerCase().equals(owner.getScreenName().toLowerCase());
    }

    private boolean isNotNull(final Tweet question) {
        return question.getScreenName() != null;
    }

    @Override
    public final String getKeyWord() {
        return null;
    }
}

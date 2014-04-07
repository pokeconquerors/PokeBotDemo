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
            if (owner.isTimeToNextRound(question.getText())) { return owner
                    .getCallForNextRound(); }
        }
        return null;
    }

    private boolean isTweetOfMe(final Tweet question) {
        return question.getScreenName().toLowerCase()
                .equals(owner.getScreenName().toLowerCase());
    }

    private boolean isNotNull(final Tweet question) {
        return question.getScreenName() != null;
    }
}

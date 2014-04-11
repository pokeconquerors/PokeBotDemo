package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersAlwaysCell implements SmartCell {
    private JudgeBot owner = null;

    public JudgeAnswersAlwaysCell(final JudgeBot owner) {
        this.owner = owner;
    }

    public final String ask(final Tweet question) {
        if (isNotNull(question) && isNotAWait()) {
            return "@" + question.getScreenName()
                    + " Bienvenue dans le monde fascinant des pokémons";
        } else if (isNotAWait()) {
            return "Un pokemon sauvage apparait";
        }
        return null;
    }

    private boolean isNotAWait() {
        return !owner.getWait();
    }

    private boolean isNotNull(final Tweet question) {
        return question.getScreenName() != null;
    }

    @Override
    public final String getKeyWord() {
        return "<no Keyword>";
    }
}

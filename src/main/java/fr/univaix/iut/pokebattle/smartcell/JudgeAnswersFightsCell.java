package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersFightsCell implements SmartCell {
    private JudgeBot owner;

    public JudgeAnswersFightsCell(final JudgeBot owner) {
        this.owner = owner;
    }

    public final String ask(final Tweet question) {
        if (isNotNull(question) && isAFight(question)) {
            owner.updateDateList(question.getCreatedAt());
            return getMessage(question);
        }
        return null;
    }

    private String getMessage(final Tweet question) {
        return "@" + question.getScreenName() + " Nombre de combats de la dernière heure : "
                + getNbFights();
    }

    private int getNbFights() {
        return owner.getDate5fight().size();
    }

    private boolean isNotNull(final Tweet question) {
        return question.getScreenName() != null;
    }

    private boolean isAFight(final Tweet question) {
        return question.getText().matches(getKeyWord());
    }

    @Override
    public final String getKeyWord() {
        return ".*\\s+(?i)fight\\s*?.*";
    }
}

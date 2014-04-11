package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersHelpCell implements SmartCell {
    private JudgeBot owner = null;

    public JudgeAnswersHelpCell(final JudgeBot owner) {
        this.owner = owner;
    }

    @Override
    public final String ask(final Tweet question) {
        if (isNotNull(question) && isAskToHelp(question)) {
            return getHelpMessage();
        }
        return null;
    }

    private boolean isAskToHelp(final Tweet question) {
        return question.getText().matches(getKeyWord());
    }

    private boolean isNotNull(final Tweet question) {
        return question.getScreenName() != null;
    }

    private String getHelpMessage() {
        String tmp = "";
        SmartCell[] smartcells = owner.getSmartCells();
        for (SmartCell smartcell : smartcells) {
            tmp += smartcell.getKeyWord() + " ";
        }
        return tmp;
    }

    @Override
    public final String getKeyWord() {
        return ".*\\s+(?i)help.*";
    }

}

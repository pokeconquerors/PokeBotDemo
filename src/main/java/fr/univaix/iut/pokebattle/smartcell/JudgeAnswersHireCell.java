package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersHireCell implements SmartCell {

    private JudgeBot owner;

    public JudgeAnswersHireCell(final JudgeBot owner) {
        this.owner = owner;
    }

    public final String ask(final Tweet question) {
        if (isNotNull(question) && isHiring(question)) {
            if (hasNoArena()) {
                owner.setArene("@" + question.getScreenName());
                updateOwnerDescription();
                return getNewHire(question);
            } else {
                return getAlreadyHire(question);
            }
        }
        return null;
    }

    private String getAlreadyHire(final Tweet question) {
        return "@" + question.getScreenName() + " " + owner.getArene()
                + " is my owner but maybe ...";
    }

    private String getNewHire(final Tweet question) {
        return "@" + question.getScreenName() + " my gym is " + "@"
                + question.getScreenName();
    }

    private boolean hasNoArena() {
        return owner.getArene() == null;
    }

    private void updateOwnerDescription() {
        try {
            owner.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isNotNull(final Tweet question) {
        return (question.getScreenName() != null);
    }

    private boolean isHiring(final Tweet question) {
        return question.getText().matches(getKeyWord());
    }

    @Override
    public final String getKeyWord() {
        return ".*\\s+(?i)hire\\s*!.*";
    }
}

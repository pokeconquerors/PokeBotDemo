package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeAnswersGymCell implements SmartCell {
    private JudgeBot owner;

    public JudgeAnswersGymCell(final JudgeBot owner) {
        this.owner = owner;
    }

    public final String ask(final Tweet question) {
        if (isNotNull(question) && isAnArena(question)) {
            return getGymMessage(question);
        }
        return null;
    }

    private String getGymMessage(final Tweet question) {
        if (hasGym()) {
            return "@" + question.getScreenName() + " my Gym is " + owner.getArene()
                    + " but maybe ...";
        }
        updateOwner();
        return "@" + question.getScreenName() + " no Gym";
    }

    private void updateOwner() {
        owner.setArene("no Gym");
        try {
            owner.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean hasGym() {
        return owner.getArene() != null;
    }

    private boolean isNotNull(final Tweet question) {
        return question.getScreenName() != null;
    }

    private boolean isAnArena(final Tweet question) {
        return question.getText().matches(getKeyWord());
    }

    @Override
    public final String getKeyWord() {
        return ".*\\s+(?i)gym\\s*\\?.*";
    }
}

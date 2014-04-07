package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeValidateFightCell implements SmartCell {
    private JudgeBot owner;

    public JudgeValidateFightCell(final JudgeBot owner) {
        this.owner = owner;
    }

    public final String ask(final Tweet question) {
        if (!owner.isInFight()) {
            if (isOkToFight(question) && isNotNull(question)) {
                owner.updateDateList(question.getCreatedAt());
                if (owner.hasAlreadyDone5Fights()) { return "@"
                        + question.getScreenName()
                        + " Conseil du prof chen : pas plus de 5 combat en une heure !"; }
                addNewPokemon(question);
                startFight(question);
                return getReturnMessage(question);
            }
        } else if (isOkToFight(question) && isNotNull(question)) {
            return "Je suis déjà en combat, "
                    + "veuillez me contacter plus tard jeune "
                    + "dresseur bipéde, cordialement";
            }
        return null;
    }

    private void startFight(final Tweet question) {
        owner.addDate5fight(question.getCreatedAt());
        owner.setInFight(true);
        owner.incrNbRoundsEnCours();
    }

    private void addNewPokemon(final Tweet question) {
        owner.pushPokemon(owner.getPoke(question.getText()),
                "@" + question.getScreenName(), null, null);
    }

    private String getReturnMessage(final Tweet question) {
        return owner.getCallForNextRound();
    }

    private boolean isNotNull(final Tweet question) {
        return question.getScreenName() != null;
    }

    private boolean isOkToFight(final Tweet question) {
        return question.getText().matches(".*\\s+#fight #ok with\\s+.*");
    }
}

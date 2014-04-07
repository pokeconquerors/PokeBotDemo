package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeValidateFightCell implements SmartCell {
    private JudgeBot owner;

    public JudgeValidateFightCell(JudgeBot owner) {
        this.owner = owner;
    }

    public String ask(Tweet question) {
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
        } else if (isOkToFight(question) && isNotNull(question)) { return "Je suis déjà en combat, veuillez me contacter plus tard jeune dresseur bipéde, cordialement"; }
        return null;
    }

    private void startFight(Tweet question) {
        owner.addDate5fight(question.getCreatedAt());
        owner.setInFight(true);
        owner.incrNbRoundsEnCours();
    }

    private void addNewPokemon(Tweet question) {
        owner.pushPokemon(owner.getPoke(question.getText()),
                "@" + question.getScreenName(), null, null);
    }

    private String getReturnMessage(Tweet question) {
        return owner.getCallForNextRound();
    }

    private boolean isNotNull(Tweet question) {
        return question.getScreenName() != null;
    }

    private boolean isOkToFight(Tweet question) {
        return question.getText().matches(".*\\s+#fight #ok with\\s+.*");
    }
}

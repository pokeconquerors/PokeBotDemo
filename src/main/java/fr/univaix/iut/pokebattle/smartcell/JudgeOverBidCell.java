package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeOverBidCell implements SmartCell {
    private JudgeBot owner = null;

    public JudgeOverBidCell(final JudgeBot owner) {
        this.owner = owner;
    }

    @Override
    public final String ask(final Tweet question) {
        if (isNotNull(question) && isAnOverBid(question)) {
            int montant = getMontant(question.getText());
            if (owner.isInterestedBy(montant)) {
                owner.setSalaire(montant);
                return "@" + question.getScreenName()
                        + " Nous allons bien nous entendre ! " + hire(question);
            } else {
                return "@" + question.getScreenName()
                        + " Sale Radin ! Retourne à l'âge de Pierre";
            }
        }
        return null;
    }

    private String hire(final Tweet question) {
        JudgeHireCell cell = new JudgeHireCell(owner);
        owner.setArene(null);
        String hireMess = cell.ask(
                new Tweet(question.getScreenName(), "@PokeConquerors Hire !"))
                .replaceFirst("@" + question.getScreenName() + " ", "");
        return hireMess;
    }

    private int getMontant(final String text) {
        String[] splitedText = text.split("\\s+");
        for (String tmpText : splitedText) {
            if (isANumber(tmpText)) {
                return Integer.parseInt(tmpText);
            }
        }
        return 0;
    }

    private boolean isANumber(final String text) {
        for (char c : text.toCharArray()) {
            if (!Character.isDigit(c)) {
                System.out.println(c);
                return false;
            }
        }
        return true;
    }

    private boolean isAnOverBid(final Tweet question) {
        return question.getText().matches(getKeyWord());
    }

    private boolean isNotNull(final Tweet question) {
        return question.getScreenName() != null;
    }

    @Override
    public String getKeyWord() {
       return ".*(?i)overbid\\s+.*";
    }
}

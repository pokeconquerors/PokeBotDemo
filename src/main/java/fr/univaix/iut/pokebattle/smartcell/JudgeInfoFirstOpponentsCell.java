package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeInfoFirstOpponentsCell implements SmartCell {
    private JudgeBot owner;

    public JudgeInfoFirstOpponentsCell(JudgeBot owner) {
        this.owner = owner;
    }

    public String getPokemon1(String text) {
        String[] tab = text.split(" ");
        return tab[3];
    }

    public String ask(Tweet question) {
        if (question.getText().toLowerCase().contains(" #fight with ")) {
            owner.pushPokemon(getPokemon1(question.getText()),
                    "@" + question.getScreenName(), null, null);
            return "Pokemon = "
                    + owner.getPokemonFromList(getPokemon1(question.getText()))
                    + ", Proprio = "
                    + owner.getProprietaireFromList(getPokemon1(question
                            .getText()));
        }
        return null;

    }
}

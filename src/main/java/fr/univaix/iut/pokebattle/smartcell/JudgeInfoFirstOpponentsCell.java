package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeInfoFirstOpponentsCell implements SmartCell {
    private static final int POSITION_POKEMON_IN_SPLITTAB = 3;
    private JudgeBot         owner;

    public JudgeInfoFirstOpponentsCell(final JudgeBot owner) {
        this.owner = owner;
    }

    public final String getPokemon1(final String text) {
        String[] tab = text.split(" ");
        return tab[POSITION_POKEMON_IN_SPLITTAB];
    }

    public final String ask(final Tweet question) {
        if (isFightWith(question)) {
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

    private boolean isFightWith(final Tweet question) {
        return question.getText().matches(getKeyWord());
    }

    @Override
    public String getKeyWord() {
        return ".*(?i)#fight with .*";
    }
}

package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public class JudgeGetFightWithCell implements SmartCell {
    private static final int POSITION_POKEMON_IN_SPLITTAB = 3;
    private JudgeBot         owner;

    public JudgeGetFightWithCell(final JudgeBot owner) {
        this.owner = owner;
    }

    public final String getPokemon1(final String text) {
        String[] tab = text.split(" ");
        return tab[POSITION_POKEMON_IN_SPLITTAB];
    }

    public final String ask(final Tweet question) {
        if (isFightWith(question)) {
            addPokemon(question);
        }
        return null;
    }

    private void addPokemon(final String pokemon, final String proprietaire) {
        owner.pushPokemon(pokemon, proprietaire, null, null, true);
    }

    private void addPokemon(final Tweet question) {
        addPokemon(getPokemon1(question.getText()), "@" + question.getScreenName());
    }

    private boolean isFightWith(final Tweet question) {
        return question.getText().matches(getKeyWord());
    }

    @Override
    public final String getKeyWord() {
        return ".*(?i)#fight with .*";
    }
}

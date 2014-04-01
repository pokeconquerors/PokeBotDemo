package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.data.DataObjectAttack;
import fr.univaix.iut.pokebattle.data.DataReadObject;
import fr.univaix.iut.pokebattle.twitter.Tweet;

/**
 * Reply to all.
 */
public class JudgeAnswerValidAttaque implements SmartCell {
	private JudgeBot owner;

	public JudgeAnswerValidAttaque(JudgeBot owner) {
		this.owner = owner;
	}

	DataReadObject dro = DataReadObject.getInstance();

	public String getElementInArray(String text, int indice) {
		System.out.println(text);
		String[] tab = text.split(" ");
		return tab[indice];
	}


	public String ask(Tweet question) {

		try {
			if (question.getText().toLowerCase().matches("@.+ #attack #.+ @.+.*")
					&& question.getScreenName() != null) {

				String nomAttaque = getElementInArray(question.getText(), 2)
						.substring(1);
				String nomPokemon = getElementInArray(question.getText(), 0)
						.substring(1);
				DataObjectAttack attaque = dro.getAttaque(nomPokemon,
						nomAttaque);
				
				if (attaque != null) {
					return "@" + owner.getOtherPokemon(nomPokemon)
							+ " -10pv /cc " + "@" + question.getScreenName();
				}
				else
				{
					return "@" + owner.getOtherPokemon(nomPokemon)
							+ " -0pv /cc " + "@" + question.getScreenName();
				}

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}

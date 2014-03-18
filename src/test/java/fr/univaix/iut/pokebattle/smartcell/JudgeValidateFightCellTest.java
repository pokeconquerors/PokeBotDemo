package fr.univaix.iut.pokebattle.smartcell;

import static org.junit.Assert.assertEquals;
import fr.univaix.iut.pokebattle.bot.JudgeBot;
import fr.univaix.iut.pokebattle.twitter.Tweet;

import org.junit.Test;

public class JudgeValidateFightCellTest {
	
	JudgeValidateFightCell cell = new JudgeValidateFightCell(new JudgeBot());
	
	@Test
	public void testSalut() {
        assertEquals("@nedseb VS @pcreux => OK ! Let's ready for the next battle !", cell.ask(new Tweet("nedseb", "@pcreux #fight #ok with @pikachuNyanNian /cc @viviane")));
    }
}

package fr.univaix.iut.pokebattle.twitter;

import fr.univaix.iut.pokebattle.tuse.Credentials;
import org.junit.Test;

import java.io.InputStream;

import static org.fest.assertions.Assertions.assertThat;

public class CredentialsTest {
    @Test
    public void testLoadCredentialsGivenAValidPropertiesFileShouldReturnAValidCredentials()
            throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                "./testCredentials.properties");
        Credentials credentials = Credentials.loadCredentials(inputStream);
        assertThat(credentials.getConsumerKey()).isEqualTo("l5Ru6CFLV2cqdwbpYA0zg");
        assertThat(credentials.getConsumerSecret()).isEqualTo(
                "dmhQaM8sGbxcnmGvmTTRBSRNNHj3QFNbhSJfeNLoaQ");
        assertThat(credentials.getToken()).isEqualTo(
                "2363180089-BcQpKRKLAMQk4SWHtZBDt8hS4S6XXn4kiFR9XvG");
        assertThat(credentials.getTokenSecret()).isEqualTo(
                "V2T8WocryKa8HIYRPoPE8CV0tmg7aiY0Smn9SgiQ83Apc");
    }
}

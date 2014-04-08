package fr.univaix.iut.pokebattle.twitter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.univaix.iut.pokebattle.bot.Bot;
import fr.univaix.iut.pokebattle.tuse.Credentials;
import fr.univaix.iut.pokebattle.tuse.TwitterUserStreamEasy;
import fr.univaix.iut.pokebattle.tuse.UserStreamAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.UserStreamListener;

public class TwitterUserStreamEasyBuilder {
    private static final Logger LOGGER = LoggerFactory
                                               .getLogger(TwitterBot.class);
    private Credentials         credentials;
    private Twitter             twitter;
    private Bot                 bot;
    private Boolean             debug = false;

    public Boolean getDebug() {
		return debug;
	}

	public void setDebug(Boolean debug) {
		this.debug = debug;
	}

	public TwitterUserStreamEasyBuilder(final Credentials credentials,
            final Twitter twitter, final Bot bot) {
        this.credentials = credentials;
        this.twitter = twitter;
        this.bot = bot;
    }

    public final TwitterUserStreamEasy build() {
        UserStreamListener listener = new UserStreamAdapter() {
            @Override
            public void onStatus(final Status status) {
                LOGGER.info("TwitterUserStreamEasyExample.onStatus()");
                try {
                    processNewQuestion(status, bot);
                } catch (TwitterException e) {
                    LOGGER.error("Twitter Error", e);
                }
            }
        };
        return new TwitterUserStreamEasy(listener, credentials);
    }

    public final void processNewQuestion(final Status status, final Bot bot)
            throws TwitterException {
        if (isNotANewQuestion(status)) {
            if (isNotAnInterestingTweetOfMe(status, bot)) {
                LOGGER.info("Ignored status change");
                return;
            }
        }
        this.bot.setTwitter(twitter);
        this.bot.setId(twitter.getId());
        this.bot.setScreenName(twitter.getScreenName());
        String response = bot.ask(new Tweet(status.getUser().getScreenName(),
                status.getText(), status.getCreatedAt()));

        if (responseIsNotNull(response)) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            System.out.println(response + " " + dateFormat.format(date)
                    + " #PokeBattle");
            if(!debug) {
            twitter.updateStatus(response + " " + dateFormat.format(date)
                    + " #PokeBattle");
            }
        }
    }

    private boolean isNotAnInterestingTweetOfMe(final Status status, final Bot bot)
            throws TwitterException {
        return !(isTweetOfMe(status) && bot.isTimeToNextRound(status.getText()));
    }

    public final boolean responseIsNotNull(final String response) {
        return response != null;
    }

    public final boolean isNotANewQuestion(final Status status) throws TwitterException {
        return isTweetOfMe(status) || !isTweetForMe(status);
    }

    private boolean isTweetForMe(final Status status) throws TwitterException {
        return status.getText().toLowerCase()
                .contains(twitter.getScreenName().toLowerCase());
    }

    public final boolean isTweetOfMe(final Status status) throws TwitterException {
        return status.getUser().getId() == twitter.getId();
    }
}

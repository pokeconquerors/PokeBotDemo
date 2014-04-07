package fr.univaix.iut.pokebattle.twitter;

import java.util.Date;

public class Tweet {
    private String text;
    private String screenName;
    private Date   createdAt;

    public Tweet(final String text) {
        this.text = text;
    }

    public Tweet(final String screenName, final String text) {
        this.screenName = screenName;
        this.text = text;
    }

    public Tweet(final String screenName, final String text, final Date createdAt) {
        this.screenName = screenName;
        this.text = text;
        this.setCreatedAt(createdAt);
    }

    public final String getScreenName() {
        return screenName;
    }

    public final String getText() {
        return text;
    }

    public final Date getCreatedAt() {
        return createdAt;
    }

    public final void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }
}

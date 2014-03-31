package fr.univaix.iut.pokebattle.twitter;

import java.util.Date;

public class Tweet {
    private String text;
    private String screenName;
    private Date createdAt;

    public Tweet(String text) {
        this.text = text;
    }

    public Tweet(String screenName, String text) {
        this.screenName = screenName;
        this.text = text;
    }

    public Tweet(String screenName, String text, Date createdAt) {
    	this.screenName = screenName;
        this.text = text;
        this.setCreatedAt(createdAt);
	}

	public String getScreenName() {
        return screenName;
    }

    public String getText() {
        return text;
    }

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt =createdAt;
	}
}

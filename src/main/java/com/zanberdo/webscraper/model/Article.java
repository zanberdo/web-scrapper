package com.zanberdo.webscraper.model;

import java.util.Objects;

public class Article {
    private String headline;
    private String byline;
    private String link;
    private String description;

    public Article() {
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(headline, article.headline) &&
                Objects.equals(byline, article.byline) &&
                Objects.equals(link, article.link) &&
                Objects.equals(description, article.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(headline, byline, link, description);
    }

    @Override
    public String toString() {
        return "Article{" +
                "headline='" + headline + '\'' +
                ", byline='" + byline + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

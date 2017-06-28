package com.zanberdo.webscraper.model;

public class Article {
    private String headline;
    private String byline;
    private String link;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (headline != null ? !headline.equals(article.headline) : article.headline != null) return false;
        if (byline != null ? !byline.equals(article.byline) : article.byline != null) return false;
        return link != null ? link.equals(article.link) : article.link == null;

    }

    @Override
    public int hashCode() {
        int result = headline != null ? headline.hashCode() : 0;
        result = 31 * result + (byline != null ? byline.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Article{");
        sb.append("headline='").append(headline).append('\'');
        sb.append(", byline='").append(byline).append('\'');
        sb.append(", link='").append(link).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

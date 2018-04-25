package com.zanberdo.webscraper.scaper.controller;

import com.zanberdo.webscraper.exception.NoContentException;
import com.zanberdo.webscraper.model.Article;
import com.zanberdo.webscraper.scaper.interfaces.DocumentManagementControllerInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by mark.zanfardino on 11/7/16.
 */
public class DocumentManagementController implements DocumentManagementControllerInterface {
    private static final Logger log = LogManager.getLogger(DocumentManagementController.class);
    private static final String CONTENT_MODEL = "CNN.contentModel";

    @Override
    public List<Article> parse(final Document document, Integer limit) {
        log.info("  Parsing homepage document into article list.");
        final List<Article> articles = new ArrayList<>();
        final Elements scripts = document.getElementsByTag("script");

        String data = null;
        for (Element script : scripts) {
            if (script.data().contains(CONTENT_MODEL)) {
                data =  script.data();
                break;
            }
        }
        if (Objects.nonNull(data) && !data.isEmpty()) {
            final JSONObject content = new JSONObject(data.substring(data.indexOf("articleList") -2, data.indexOf(", registryURL")));
            final JSONArray articleList = content.getJSONArray("articleList");
            if (Objects.isNull(limit) || limit > articleList.length()) {
                limit = articleList.length();
            }
            for (int i=0; i < limit; i++) {
                final Article article = new Article();
                article.setLink(articleList.getJSONObject(i).get("uri").toString());
                article.setHeadline(articleList.getJSONObject(i).get("headline").toString());
                article.setDescription(articleList.getJSONObject(i).get("description").toString());
                articles.add(article);
            }
        }

        return articles;
    }

//    @Override
//    public List<String> parse(Document document, Integer limit) {
//        log.info("  Parsing homepage document.");
//
//        String content = null;
//        List<String> links = new ArrayList<>();
//
//        Elements scripts = document.getElementsByTag("script");
//        for (Element script : scripts) {
//            if (script.data().contains("CNN.contentModel")) {
//                content = script.data();
//                break;
//            }
//        }
//        if (content != null && !content.isEmpty()) {
//            JSONObject json = new JSONObject(content.substring(content.indexOf("articleList") -2, content.indexOf(", registryURL")));
//            JSONArray articleList = json.getJSONArray("articleList");
//            if (limit == null || limit > articleList.length()) {
//                limit = articleList.length();
//            }
//            for (int i=0; i < limit; i++) {
//                links.add(articleList.getJSONObject(i).get("uri").toString());
//            }
//        } else {
//            throw new NoContentException();
//        }
//        return links;
//    }

    @Override
    public Article parse(Document document) {
        log.info("  Parsing article document.");

        Article article = new Article();

        String headline = null;
        Element docHeadline = document.getElementsByClass("pg-headline").first();
        if (docHeadline == null) {
            docHeadline = document.getElementsByClass("media__video-headline").first();
        }
        if (docHeadline != null) {
            headline = docHeadline.text();
        }

        String byline = null;
        Element docByline = document.getElementsByClass("metadata__byline__author").first();
        if (docByline == null) {
            docByline = document.getElementsByClass("metadata__source-name").first();
        }
        if (docByline != null) {
            byline = docByline.text();
        }
        article.setHeadline(headline);
        article.setByline(byline);

        return article;
    }
}

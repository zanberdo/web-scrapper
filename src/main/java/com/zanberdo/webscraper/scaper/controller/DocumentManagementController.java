package com.zanberdo.webscraper.scaper.controller;

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
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by mark.zanfardino on 11/7/16.
 */
public class DocumentManagementController implements DocumentManagementControllerInterface {
    private static final Logger log = LogManager.getLogger(DocumentManagementController.class);
    private static final String CONTENT_MODEL = "CNN.contentModel";
    private static final String TARGET_SITE = "https://www.cnn.com";
    private static final int MAX_DELAY = 2;

    @Override
    public List<Article> parse(final Document document, Integer limit) {
        final Random random = new Random();

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
                System.out.print(".");
                final int delay = random.nextInt(MAX_DELAY) + 1;
                log.info("Delaying {} seconds...", delay);

                try {
                    TimeUnit.SECONDS.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                final Article article = new Article();
                if (Objects.nonNull(articleList.getJSONObject(i).get("uri")))
                    article.setLink(TARGET_SITE + articleList.getJSONObject(i).get("uri").toString());
                if (Objects.nonNull(articleList.getJSONObject(i).get("headline")))
                    article.setHeadline(articleList.getJSONObject(i).get("headline").toString());
                if (Objects.nonNull(articleList.getJSONObject(i).get("description")))
                    article.setDescription(articleList.getJSONObject(i).get("description").toString());

                articles.add(article);
            }
        }

        return articles;
    }

//    @Override
//    public Article parse(final Document document) {
//        log.info("  Parsing article document.");
//
//        Article article = new Article();
//
//        String headline = null;
//        Element docHeadline = document.getElementsByClass("pg-headline").first();
//        if (docHeadline == null) {
//            docHeadline = document.getElementsByClass("media__video-headline").first();
//        }
//        if (docHeadline != null) {
//            headline = docHeadline.text();
//        }
//
//        String byline = null;
//        Element docByline = document.getElementsByClass("metadata__byline__author").first();
//        if (docByline == null) {
//            docByline = document.getElementsByClass("metadata__source-name").first();
//        }
//        if (docByline != null) {
//            byline = docByline.text();
//        }
//        article.setHeadline(headline);
//        article.setByline(byline);
//
//        return article;
//    }
}

package com.zanberdo.webscraper.controller;

import com.zanberdo.webscraper.model.Article;
import com.zanberdo.webscraper.scaper.controller.DocumentManagementController;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class TestDocumentManagementController {
    private static DocumentManagementController documentManagementController = new DocumentManagementController();
    private static String path;
    private static final String HOME_PAGE = "/src/test/resource/homepage.html";
    private static final String JSON_DATA = "/src/test/resource/expected.json";
    private static final String TARGET_SITE = "https://www.cnn.com";

    @Before
    public void setup() {
        this.path = System.getProperty("user.dir");
    }

    @Test
    public void testGetArticlesFromHomepage() throws Exception {
        final File htmlFile = new File(path + HOME_PAGE);
        final Document homepage = Jsoup.parse(htmlFile, "UTF-8", TARGET_SITE);
        final List<Article> expected = new ArrayList<>();

        final List<Article> actual = documentManagementController.parse(homepage, 5);

        final JSONParser parser = new JSONParser();
        final JSONObject jsonData = (JSONObject) parser.parse(new FileReader(path + JSON_DATA));
        final JSONArray expectedArticles = (JSONArray) jsonData.get("articles");
        for (JSONObject newsArticle : (Iterable<JSONObject>) expectedArticles) {
            final Article article = new Article();
            article.setHeadline(newsArticle.get("headline").toString());
            article.setByline(newsArticle.get("byline").toString());
            article.setLink(TARGET_SITE + newsArticle.get("link").toString());
            article.setDescription(newsArticle.get("description").toString());
            expected.add(article);
        }
        assertEquals(expected.toString(), actual.toString());
    }

}

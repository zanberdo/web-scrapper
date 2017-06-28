package com.zanberdo.webscraper.controller;

import com.zanberdo.webscraper.model.Article;
import com.zanberdo.webscraper.scaper.controller.DocumentManagementController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestDocumentManagementController {
    private static DocumentManagementController documentManagementController = new DocumentManagementController();
    private static String path;
    private static final String TARGET_SITE = "http://www.cnn.com";

    @Before
    public void setup() throws Exception {
        this.path = System.getProperty("user.dir");
//        System.out.println(Jsoup.connect("http://www.cnn.com//2016/11/08/politics/gallery/election-day-2016/index.html").get());
    }

    @Test
    public void testGetLinksFromHomepage() throws Exception {
        List<String> expected = new ArrayList<>();
        expected.add("/2016/11/07/politics/live-election-results-coverage/index.html");
        expected.add("/videos/politics/2016/11/07/how-cnn-calls-a-presidential-election-mobile-origwx-jm.cnn");
        expected.add("/2016/11/08/politics/nevada-voting-lawsuit-donald-trump/index.html");
        expected.add("/2016/11/08/politics/eric-trump-ballot-tweet/index.html");
        expected.add("/2016/11/08/politics/donald-trump-wife-son-voting-new-york-memes-2016-election-cnntv/index.html");
        File file = new File(path + "/src/test/resource/homepage.html");
        Document homepage = Jsoup.parse(file, "UTF-8", "http://www.cnn.com/");

        List<String> actual = documentManagementController.parse(homepage, 5);

        assertEquals("Actual list of links equals expected list of links.", expected, actual);

        expected.remove(expected.size() -1);
        List<String> fail = documentManagementController.parse(homepage, 4);
        assertEquals("Actual list of links equals modified expected list of links.", expected, fail);
    }

    @Test
    public void testGetArticleDetailsFromNewsArticle() throws Exception {
        Article expected = new Article();
        expected.setHeadline("Election 2016: Live coverage");
        expected.setByline("By Tal Kopan, CNN");

        File file = new File(path + "/src/test/resource/articleNews.html");
        Document article = Jsoup.parse(file, "UTF-8", "http://www.cnn.com/");

        Article actual = documentManagementController.parse(article);
        assertEquals("Actual article details equals expected article details.", expected, actual);
    }

    @Test
    public void testGetArticleDetailsFromVideoArticle() throws Exception {
        Article expected = new Article();
        expected.setHeadline("How CNN calls a presidential election");
        expected.setByline("Source: CNN");

        File file = new File(path + "/src/test/resource/articleVideo.html");
        Document article = Jsoup.parse(file, "UTF-8", "http://www.cnn.com/");

        Article actual = documentManagementController.parse(article);
        assertEquals("Actual article details equals expected article details.", expected, actual);
    }

    @Test
    public void testGetArticleDetailsFromGalleryArticle() throws Exception {
        Article expected = new Article();
        expected.setHeadline("Election Day in America");

        File file = new File(path + "/src/test/resource/articleGallery.html");
        Document article = Jsoup.parse(file, "UTF-8", "http://www.cnn.com/");

        Article actual = documentManagementController.parse(article);
        assertEquals("Actual article details equals expected article details.", expected, actual);
    }

    @Test
    public void testGetHomepageWithoutContentThrowsException() throws Exception {
        // TODO: write test to validate that NoContentException is thrown correctly.
    }

}

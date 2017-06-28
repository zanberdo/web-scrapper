package com.zanberdo.webscraper;

import com.zanberdo.webscraper.lifecycle.Scraper;
import com.zanberdo.webscraper.mock.MockDocumentManagementController;
import com.zanberdo.webscraper.mock.MockRandom;
import com.zanberdo.webscraper.mock.MockTargetSiteView;
import com.zanberdo.webscraper.model.Article;
import com.zanberdo.webscraper.scaper.interfaces.DocumentManagementControllerInterface;
import com.zanberdo.webscraper.scaper.interfaces.TargetSiteViewInterface;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by mark.zanfardino on 11/8/16.
 */
public class TestScraper {
    private static TargetSiteViewInterface mockTargetSiteView;
    private static DocumentManagementControllerInterface mockDocumentManagementController;
    private static Random mockRandom;

    @Before
    public void setup() throws Exception {
        mockTargetSiteView = new MockTargetSiteView();
        mockDocumentManagementController = new MockDocumentManagementController();
        mockRandom = new MockRandom();
    }

    @Test
    public void testScrapper() throws Exception {
        ArrayList<Article> expected = new ArrayList<>();
        Article expectedArticle = new Article();
        expectedArticle.setHeadline("Mock Headline");
        expectedArticle.setByline("Mock Byline");
        expectedArticle.setLink("http://www.cnn.com/Mock/Link/List");
        expected.add(expectedArticle);

        Scraper scraper = new Scraper(mockTargetSiteView, mockDocumentManagementController, mockRandom);

        ArrayList<Article> actual = scraper.scrape(null);
        assertEquals("Actual list of articles matched expected list of articles", expected, actual);

        actual.add(new Article());
        assertNotEquals("Actual list of articles does not match expected list of articles.", expected, actual);
    }
}

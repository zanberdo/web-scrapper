package com.zanberdo.webscraper.mock;

import com.zanberdo.webscraper.model.Article;
import com.zanberdo.webscraper.scaper.controller.DocumentManagementController;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark.zanfardino on 11/9/16.
 */
public class MockDocumentManagementController extends DocumentManagementController {
    private static final String MOCK_SITE = "https://www.cnn.com";

    @Override
    public List<Article> parse(final Document document, Integer limit) {
        final List<Article> mockLinkList = new ArrayList<>();
        final Article article = new Article();

        article.setHeadline("Mock Headline");
        article.setByline("null");
        article.setLink(MOCK_SITE + "/Mock/Link/List");
        article.setDescription("Mock description");
        mockLinkList.add(article);

        return mockLinkList;
    }

}

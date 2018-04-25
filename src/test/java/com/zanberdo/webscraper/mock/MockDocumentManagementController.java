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
    @Override
    public List<Article> parse(final Document document, Integer limit) {
        List<Article> mockLinkList = new ArrayList<>();
        final Article article = new Article();
        article.setLink("/Mock/Link/List");
        mockLinkList.add(article);
        return mockLinkList;
    }

    @Override
    public Article parse(final Document document) {
        Article mockArticle = new Article();
        mockArticle.setHeadline("Mock Headline");
        mockArticle.setByline("Mock Byline");
        return mockArticle;
    }
}

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
    public List<String> parse(Document document, Integer limit) {
        List<String> mockLinkList = new ArrayList<>();
        mockLinkList.add("/Mock/Link/List");
        return mockLinkList;
    }

    @Override
    public Article parse(Document document) {
        Article mockArticle = new Article();
        mockArticle.setHeadline("Mock Headline");
        mockArticle.setByline("Mock Byline");
        return mockArticle;
    }
}

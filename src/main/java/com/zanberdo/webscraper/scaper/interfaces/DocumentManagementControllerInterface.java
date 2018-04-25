package com.zanberdo.webscraper.scaper.interfaces;

import com.zanberdo.webscraper.model.Article;
import org.jsoup.nodes.Document;

import java.util.List;

/**
 * Created by mark.zanfardino on 11/7/16.
 */
public interface DocumentManagementControllerInterface {
//    List<String> parse(Document document, Integer limit);
    List<Article> parse(final Document document, Integer limit);
    Article parse(final Document document);
}

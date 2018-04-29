package com.zanberdo.webscraper.lifecycle;

import com.zanberdo.webscraper.model.Article;
import com.zanberdo.webscraper.scaper.interfaces.DocumentManagementControllerInterface;
import com.zanberdo.webscraper.scaper.interfaces.TargetSiteViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import java.util.List;

/**
 * Created by mark.zanfardino on 11/7/16.
 */
public class Scraper {
    public static final Logger log = LogManager.getLogger(Scraper.class);

    private static final String TARGET_SITE = "https://www.cnn.com";

    private final TargetSiteViewInterface targetSiteView;
    private final DocumentManagementControllerInterface documentManagementController;

    public Scraper(TargetSiteViewInterface targetSiteView, DocumentManagementControllerInterface documentManagementController) {
        this.targetSiteView = targetSiteView;
        this.documentManagementController = documentManagementController;
    }

    public List<Article> scrape(final Integer limit) {
        log.info("Begin scraping...");

        final Document homepage = targetSiteView.fetchDocument(TARGET_SITE);
        return documentManagementController.parse(homepage, limit);
    }
}

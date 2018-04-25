package com.zanberdo.webscraper.lifecycle;

import com.zanberdo.webscraper.model.Article;
import com.zanberdo.webscraper.scaper.interfaces.DocumentManagementControllerInterface;
import com.zanberdo.webscraper.scaper.interfaces.TargetSiteViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by mark.zanfardino on 11/7/16.
 */
public class Scraper {
    public static final Logger log = LogManager.getLogger(Scraper.class);

    private static final String TARGET_SITE = "https://www.cnn.com";
    private static final int MAX_DELAY = 5;

    private TargetSiteViewInterface targetSiteView;
    private DocumentManagementControllerInterface documentManagementController;
    private Random random;

    public Scraper(TargetSiteViewInterface targetSiteView, DocumentManagementControllerInterface documentManagementController, Random random) {
        this.targetSiteView = targetSiteView;
        this.documentManagementController = documentManagementController;
        this.random = random;
    }

    public ArrayList<Article> scrape(Integer limit) {
        log.info("Begin scraping...");

        ArrayList<Article> articles = new ArrayList<>();

        Document homepage = targetSiteView.fetchDocument(TARGET_SITE);
        // TODO: Return map of uri and headline - parse headline of tags.
        List<String> links = documentManagementController.parse(homepage, limit);
        //        Collections.shuffle(links);
        // TODO: iterate over map of uri/headline
        for (String link : links) {
            System.out.print(".");

            // TODO: Remove delay or reduce time...
            int delay = random.nextInt(MAX_DELAY) + 1;
            log.info("Delaying {} seconds...", delay);

            try {
                TimeUnit.SECONDS.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Document document = targetSiteView.fetchDocument(TARGET_SITE + link);
            // TODO: fetch article date
            Article article = documentManagementController.parse(document);
            article.setLink(TARGET_SITE + link);
            articles.add(article);
        }
        return articles;
    }
}

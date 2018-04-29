package com.zanberdo.webscraper;

import com.zanberdo.webscraper.lifecycle.Scraper;
import com.zanberdo.webscraper.model.Article;
import com.zanberdo.webscraper.scaper.controller.DocumentManagementController;
import com.zanberdo.webscraper.scaper.view.TargetSiteView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Starting Web Scraper Application");

        Integer limit = null;
        if (args.length == 1) {
            try {
                limit = Math.abs(Integer.parseInt(args[0]));
            } catch (NumberFormatException ex) {
                log.fatal("Invalid parameter passed: '{}'", args[0]);

                StackTraceElement[] stackTrace = ex.getStackTrace();
                for (StackTraceElement trace : stackTrace) {
                    log.fatal(trace);
                }

                Throwable causes = ex.getCause();
                while (causes != null) {
                    stackTrace = causes.getStackTrace();
                    for (StackTraceElement trace : stackTrace) {
                        log.fatal(trace);
                    }
                    causes = causes.getCause();
                }

                System.out.println(String.format("Invalid parameter passed: '%s'", args[0]));
                help();
                System.exit(1);
            }
        }
        System.out.print("Requesting top " + ((limit == null) ? "" : limit.toString() + " ") + "articles from cnn.com");
        TargetSiteView targetSiteView = new TargetSiteView();
        DocumentManagementController documentManagementController = new DocumentManagementController();
        Scraper scrapper = new Scraper(targetSiteView, documentManagementController);

        List<Article> articles = scrapper.scrape(limit);
        System.out.println(String.format("\nFound %s articles from cnn.com:\n", articles.size()));
        for (Article article : articles) {
            System.out.println(String.format("  Headline:    %s", article.getHeadline()));
            if (article.getByline() != null) System.out.println(String.format("  Byline:      %s", article.getByline()));
            if (article.getDescription() != null) System.out.println(String.format("  Description: %s", article.getDescription()));
            System.out.println(String.format("  Reference: %s", article.getLink()));
            System.out.println();
        }
    }

    private static void help() {
        System.out.println("web-scraper, version 1.0.0\n");
        System.out.println("usage: web-scraper [n]\n");
        System.out.println("  n : (optional) number of articles to return.\n");
    }
}

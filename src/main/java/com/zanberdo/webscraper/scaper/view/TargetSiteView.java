package com.zanberdo.webscraper.scaper.view;

import com.zanberdo.webscraper.scaper.interfaces.TargetSiteViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by mark.zanfardino on 11/7/16.
 */
public class TargetSiteView implements TargetSiteViewInterface {

    private static final Logger log = LogManager.getLogger(TargetSiteView.class);

    @Override
    public Document fetchDocument(String url) {
        log.info(" Fetch Document from: '{}'", url);

        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document;
    }
}

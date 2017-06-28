package com.zanberdo.webscraper.scaper.interfaces;

import org.jsoup.nodes.Document;

/**
 * Created by mark.zanfardino on 11/7/16.
 */
public interface TargetSiteViewInterface {
    Document fetchDocument(String url);
}

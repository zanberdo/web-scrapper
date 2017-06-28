package com.zanberdo.webscraper.mock;

import com.zanberdo.webscraper.scaper.interfaces.TargetSiteViewInterface;
import org.jsoup.nodes.Document;

/**
 * Created by mark.zanfardino on 11/9/16.
 */
public class MockTargetSiteView implements TargetSiteViewInterface {

    @Override
    public Document fetchDocument(String url){
        return null;
    }
}

package com.zanberdo.webscraper.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by mark.zanfardino on 11/8/16.
 */
public class NoContentException extends RuntimeException {

    private static final Logger log = LogManager.getLogger(NoContentException.class);

    public NoContentException() {
        log.fatal("Script has no relevant content. Could not parse article links.");
        System.out.println("Script has no relevant content. Could not parse article links.");
    }
}

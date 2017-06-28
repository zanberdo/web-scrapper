package com.zanberdo.webscraper.mock;

import java.util.Random;

/**
 * Created by mark.zanfardino on 11/10/16.
 */
public class MockRandom extends Random {
    @Override
    public int nextInt(int i) {
        return -1;
    }
}

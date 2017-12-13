package com.example;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PIIExtractorTest {

    PIIExtractor extractor = new PIIExtractor();

    @Test
    public void extract() throws Exception {

        extractor.extract(Arrays.asList(
                "LastName, FirstName, (703)-711-0996, Blue, 11013",
                "FirstName LastName, Purple, 14537, 713 905 0383",
                "abc",
                ""
        ));

    }
}
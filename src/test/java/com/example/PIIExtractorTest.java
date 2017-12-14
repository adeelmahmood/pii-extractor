package com.example;

import com.example.json.Entry;
import com.example.json.Result;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class PIIExtractorTest {

    PIIExtractor extractor = new PIIExtractor();

    @Test
    public void extract() throws Exception {
        Result result = extractor.extract(Arrays.asList(
                "Sam T., Washington, 85360, 353 791 6380, purple",
                "Cameron, Kathy, (613)-658-9293, red, 143123121",
                "Jamie Stevenson, yellow, 84880, 028 164 6574",
                "asdfawfsdfsdfdsjh"
        ));

        System.out.println(extractor.print(result));

        assertThat(result).isNotNull();
        assertThat(result.getEntries().size()).isEqualTo(2);
        assertThat(result.getEntries().stream().anyMatch(x -> x.getZipCode().equals("85360"))).isTrue();

        Entry entry = result.getEntries().stream().filter(x -> x.getZipCode().equals("85360")).findFirst().get();
        assertThat(entry.getFirstName()).isEqualTo("Sam T.");
        assertThat(entry.getLastName()).isEqualTo("Washington");
        assertThat(entry.getColor()).isEqualTo("purple");
        assertThat(entry.getPhoneNumber()).isEqualTo("353 791 6380");
        extractor.print(result);
    }
}
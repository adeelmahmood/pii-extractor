package com.example;

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

        assertThat(result).isNotNull();
//        assertThat(result.getEntries().size()).isEqualTo(1);
//        assertThat(result.getEntries().stream().anyMatch(x -> x.getZipCode().equals("11013"))).isTrue();
//        assertThat(result.getEntries().stream().filter(x -> x.getZipCode().equals("11013")).findFirst().get().getFirstName()).isEqualTo("FirstName");
//        assertThat(result.getEntries().stream().filter(x -> x.getZipCode().equals("11013")).findFirst().get().getLastName()).isEqualTo("LastName");
//        assertThat(result.getEntries().stream().filter(x -> x.getZipCode().equals("11013")).findFirst().get().getColor()).isEqualTo("Blue");
//        assertThat(result.getEntries().stream().filter(x -> x.getZipCode().equals("11013")).findFirst().get().getPhoneNumber()).isEqualTo("(703)-711-0996");
        extractor.print(result);
    }
}
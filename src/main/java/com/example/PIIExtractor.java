package com.example;

import com.example.matchers.LastNameThenFirstNameMatcher;
import com.example.matchers.PIIMatcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PIIExtractor {

    private final List<PIIMatcher> matchers = new ArrayList<>();

    public PIIExtractor() {
        matchers.add(new LastNameThenFirstNameMatcher());
    }

    public void extract(List<String> lines) throws IOException {
        // process all lines
        lines
                .stream()
                .map(line -> {
                    // apply all available matchers to this line
                    System.out.println("aksjdaklsjdkl");
                    matchers
                            .stream()
                            .filter(m -> m.match(line))
                            .findAny()
                            .ifPresent(m -> {
                                System.out.println("test");
                                System.out.println(line + " :: " + m.getClass().getSimpleName());
                            });
                    ;
                    return "abc";
                });
    }
}

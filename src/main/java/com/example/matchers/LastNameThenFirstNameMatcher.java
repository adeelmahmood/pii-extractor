package com.example.matchers;

import java.util.regex.Pattern;

public class LastNameThenFirstNameMatcher extends AbstractPIIMatcher {

    private final Pattern pattern = Pattern.compile("\\d[5]");

    @Override
    protected boolean applyConditions(String[] tokens) {
        // conditions for this matcher (5 tokens and last one is the zip)
        return tokens.length == 5 && pattern.matcher(tokens[4]).matches();
    }
}
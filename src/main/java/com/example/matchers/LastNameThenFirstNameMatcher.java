package com.example.matchers;

import com.example.json.Entry;

import java.util.regex.Pattern;

public class LastNameThenFirstNameMatcher extends AbstractPIIMatcher {

    private final Pattern pattern = Pattern.compile("[0-9]{5}");

    @Override
    protected boolean applyConditions(String[] tokens) {
        // conditions for this matcher (5 tokens and last one is the zip)
        return tokens.length == 5 && pattern.matcher(tokens[4].trim()).matches();
    }

    @Override
    public Entry parseLine(String[] tokens) {
        Entry e = new Entry();
        e.setFirstName(tokens[1].trim());
        e.setLastName(tokens[0].trim());
        e.setPhoneNumber(tokens[2].trim());
        e.setColor(tokens[3].trim());
        e.setZipCode(tokens[4].trim());
        return e;
    }
}
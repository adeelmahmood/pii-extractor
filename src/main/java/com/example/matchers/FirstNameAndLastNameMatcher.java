package com.example.matchers;

import com.example.json.Entry;

import java.util.regex.Pattern;

public class FirstNameAndLastNameMatcher extends AbstractPIIMatcher {

    private final Pattern pattern = Pattern.compile("^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$");

    @Override
    protected boolean applyConditions(String[] tokens) {
        // conditions for this matcher (4 tokens and last one is the phone number)
        return tokens.length == 4 && pattern.matcher(tokens[3].trim()).matches();
    }

    @Override
    protected Entry parseLine(String[] tokens) {
        Entry e = new Entry();
        e.setFirstName(tokens[0].trim().split(" ")[0].trim());
        e.setLastName(tokens[0].trim().split(" ")[1].trim());
        e.setColor(tokens[1].trim());
        e.setZipCode(tokens[2].trim());
        e.setPhoneNumber(tokens[3].trim());
        return e;
    }
}
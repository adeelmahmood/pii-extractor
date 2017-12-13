package com.example.matchers;

import com.example.json.Entry;

public abstract class AbstractPIIMatcher implements PIIMatcher {

    @Override
    public final boolean match(String line) {
        if (line == null)
            throw new IllegalArgumentException("line must be non null");

        // split the line into tokens
        String[] tokens = line.split(",");

        // apply matching conditions
        return applyConditions(tokens);
    }

    @Override
    public final Entry parse(String line) {
        if (line == null)
            throw new IllegalArgumentException("line must be non null");

        // split the line into tokens
        String[] tokens = line.split(",");

        // parse tokens into json
        return parseLine(tokens);
    }

    protected abstract boolean applyConditions(String[] tokens);

    protected abstract Entry parseLine(String[] tokens);
}
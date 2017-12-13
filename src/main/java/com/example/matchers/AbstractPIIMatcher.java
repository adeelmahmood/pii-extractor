package com.example.matchers;

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

    protected abstract boolean applyConditions(String[] tokens);
}
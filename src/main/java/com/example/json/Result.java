package com.example.json;

import java.util.ArrayList;
import java.util.List;

public class Result {

    private List<Entry> entries = new ArrayList<>();

    private List<Integer> errors = new ArrayList<>();

    public Result() {
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Integer> getErrors() {
        return errors;
    }

    public void setErrors(List<Integer> errors) {
        this.errors = errors;
    }
}

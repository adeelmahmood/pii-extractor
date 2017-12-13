package com.example.matchers;

import com.example.json.Entry;

public interface PIIMatcher {

    boolean match(String line);

    Entry parse(String line);
}

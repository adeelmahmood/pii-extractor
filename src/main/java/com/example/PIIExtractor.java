package com.example;

import com.example.json.Entry;
import com.example.json.Result;
import com.example.matchers.FirstNameAndLastNameMatcher;
import com.example.matchers.FirstNameThenLastNameMatcher;
import com.example.matchers.LastNameThenFirstNameMatcher;
import com.example.matchers.PIIMatcher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PIIExtractor {

    private final List<PIIMatcher> matchers = new ArrayList<>();

    public PIIExtractor() {
        matchers.add(new LastNameThenFirstNameMatcher());
        matchers.add(new FirstNameAndLastNameMatcher());
        matchers.add(new FirstNameThenLastNameMatcher());
    }

    public Result extract(List<String> lines) throws IOException {
        Result result = new Result();
        // process all lines
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);

            // apply all available matchers to this line
            Optional<Entry> entry = matchers
                    .stream()
                    .filter(m -> m.match(line))
                    .findFirst()
                    .map(m -> m.parse(line));

            if (entry.isPresent()) {
                result.getEntries().add(entry.get());
            } else {
                result.getErrors().add(i+1);
            }
        }

        return result;
    }

    public void print(Result result) {
        ObjectMapper m = new ObjectMapper();
        DefaultPrettyPrinter.Indenter indenter =
                new DefaultIndenter("  ", DefaultIndenter.SYS_LF);
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentObjectsWith(indenter);
        printer.indentArraysWith(indenter);
        try {
            System.out.println(m.writerWithDefaultPrettyPrinter().writeValueAsString(result));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

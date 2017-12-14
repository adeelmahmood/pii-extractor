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
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Indenter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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

        // it would be better to use java streams to process
        // the lines because then we can possibly use
        // parallel streams as well but since we need index for
        // the error lines and java streams dont include the index
        // and even though its possible to use a variable and increment
        // it, it wont work with parallel streams .. because of that
        // im using a for loop to iterate thru lines
        // we would have been able to use streams if instead of the error
        // line's index, we needed the error line itself to be added to the
        // errors list

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
                result.getErrors().add(i);
            }
        }

        // sort by last name
        result.getEntries().sort(Comparator.comparing(Entry::getLastName));
        return result;
    }

    public String print(Result result) {
        ObjectMapper m = new ObjectMapper();
        Indenter indenter = new DefaultIndenter("  ", DefaultIndenter.SYS_LF);
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentObjectsWith(indenter);
        printer.indentArraysWith(indenter);
        try {
            return m.writerWithDefaultPrettyPrinter().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

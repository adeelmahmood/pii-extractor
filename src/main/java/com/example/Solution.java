package com.example;

import com.example.json.Result;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class Solution {

    public static void main(String[] args) throws IOException, ParseException {
        String inputFile = null;
        String outputFile = null;

        CommandLine cmd = parseOptions(args, getOptions());
        // check if params are passed as options
        if (cmd.hasOption("in") && cmd.hasOption("out")) {
            inputFile = cmd.getOptionValue("in");
            outputFile = cmd.getOptionValue("out");
        }
        // otherwise use the command line arguments
        else if (args.length >= 2) {
            inputFile = args[0];
            outputFile = args[1];
        } else {
            help(getOptions());
            System.exit(1);
        }

        PIIExtractor extractor = new PIIExtractor();

        // process the given file using PIIExtractor
        Result result = extractor.extract(Files.readAllLines(new File(inputFile).toPath()));

        // print the result to given output file
        String json = extractor.print(result);
        Files.write(new File(outputFile).toPath(), json.getBytes());
    }

    private static CommandLine parseOptions(String[] args, Options opts) {
        CommandLineParser parser = new GnuParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(opts, args);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
        return cmd;
    }

    private static Options getOptions() {
        Options opts = new Options();
        opts.addOption(Option.builder("in").hasArg().build());
        opts.addOption(Option.builder("out").hasArg().build());
        return opts;
    }

    private static void help(Options opts) {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("Solution", opts);
    }
}

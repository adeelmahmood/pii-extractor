package com.example;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Solution {

    public static void main(String[] args) throws IOException {

        Files.readAllLines(Paths.get("src/main/resources/input.txt"))
                .stream()
                .forEach(System.out::println);
        System.out.println(">>>>>>>");

        Files.readAllLines(Paths.get("src/main/resources/input.txt"))
                .stream()
                ;
    }

    private static CommandLine parseOptions(String[] args, Options opts) {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(opts, args);
        } catch (ParseException e) {
            help(opts);
        }
        return cmd;
    }

    private static Options getOptions() {
        Options opts = new Options();
        opts.addOption("in", "input file");
        opts.addOption("out", "output file");
        return opts;
    }

    private static void help(Options opts) {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("solution", opts);
    }
}

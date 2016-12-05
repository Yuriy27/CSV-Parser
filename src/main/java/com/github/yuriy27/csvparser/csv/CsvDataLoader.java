package com.github.yuriy27.csvparser.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvDataLoader implements DataLoader {

    private List<List<String>> csvFile;

    @Override
    public List<List<String>> load(String path) {
        csvFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                csvFile.add(parseLine(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return csvFile;
    }

    private List<String> parseLine(String line) {
        return Arrays.asList(line.split(","));
    }
}

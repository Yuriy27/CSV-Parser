package com.github.yuriy27.csvparser;

import com.github.yuriy27.csvparser.config.CsvConfiguration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Юра on 02.11.2016.
 */
public class CsvParser {

    private String path;
    private String separator = ",";

    public CsvParser() {
        path = "/";
    }

    public CsvParser(String path) {
        this.path = path;
    }

    public CsvParser(String path, String separator) {
        this.path = path;
        this.separator = separator;
    }

    public List<Student> parse() {
        List<Student> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] data = line.split(separator);
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].trim();
                }
                Student student = new Student(data[0], data[1], data[2]);
                result.add(student);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<Object> loadEntities(Class<?> clazz) {


        return null;
    }

}

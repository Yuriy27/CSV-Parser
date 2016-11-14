package com.github.yuriy27.csvparser;

import com.github.yuriy27.csvparser.config.CsvConfiguration;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Юра on 07.11.2016.
 */
public class ConfigTest {

    CsvConfiguration config = new CsvConfiguration("src/main/resources/config.xml");

    @Test
    public void testSeparator() {
        assertEquals(config.getSeparator(), ",");
    }

    @Test
    public void testCsvEntityFromFileCsvConfiguration() {
        System.out.println(config.getEntities().toString());
    }

    @Test
    public void testStudentsFromXmlConfiguration() {
        CsvParser parser = new CsvParser(config);
        List<Student> st = (List<Student>)(Object)parser.loadEntities(Student.class);
        System.out.println(st);
    }
}

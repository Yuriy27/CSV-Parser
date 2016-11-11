package com.github.yuriy27.csvparser;

import com.github.yuriy27.csvparser.config.CsvConfigurationImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Юра on 07.11.2016.
 */
public class ConfigTest {

    CsvConfigurationImpl config = new CsvConfigurationImpl("src/main/resources/config.xml");

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
       /* List<Student> students = new ArrayList();
        IParser parser = new CsvParser(config);
        students = (List<Student>) parser.loadEntities(Student.class);
        System.out.println(students);*/
        IParser parser = new CsvParser(config);
        parser.loadEntities(Student.class);
    }
}

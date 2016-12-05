package com.github.yuriy27.csvparser;

import com.github.yuriy27.csvparser.config.CsvConfiguration;
import com.github.yuriy27.csvparser.config.CsvConfigurationLoader;
import com.github.yuriy27.csvparser.ex.Order;
import com.github.yuriy27.csvparser.ex.Student;
import com.github.yuriy27.csvparser.exception.MissedAnnotationException;
import com.github.yuriy27.csvparser.csv.CsvParser;
import org.junit.Test;

import java.util.List;

public class ConfigTest {

    CsvConfiguration config = CsvConfigurationLoader.load("src/main/resources/config.xml");

    @Test
    public void testStudentsFromXmlConfiguration() {
        CsvParser parser = new CsvParser(config);
        System.out.println((List<Student>)(Object) parser.loadEntities(Student.class));
    }

    @Test
    public void testStudentsLoadedFromAnnotations() {
        try {
            CsvConfiguration con = CsvConfigurationLoader.loadFromAnnotations(Student.class);
            CsvParser parser = new CsvParser(con);
            System.out.println((List<Student>)(Object) parser.loadEntities());
        } catch (MissedAnnotationException missedCsvEntityAnnotation) {
            missedCsvEntityAnnotation.printStackTrace();
        }
    }

    @Test
    public void testOrderssLoadedFromAnnotations() {
        try {
            CsvConfiguration con = CsvConfigurationLoader.loadFromAnnotations(Order.class);
            CsvParser parser = new CsvParser(con);
            System.out.println((List<Student>)(Object) parser.loadEntities());
        } catch (MissedAnnotationException e) {
            e.printStackTrace();
        }
    }
}

package com.github.yuriy27.csvparser;

import com.github.yuriy27.csvparser.config.CsvConfiguration;
import com.github.yuriy27.csvparser.config.CsvConfigurationLoader;
import com.github.yuriy27.csvparser.exception.MissedAnnotationException;
import org.junit.Test;

import java.util.List;

public class ConfigTest {

    CsvConfiguration config = CsvConfigurationLoader.load("src/main/resources/config.xml");

    @Test
    public void testStudentsFromXmlConfiguration() {
        System.out.println(config.getCsvEntities());
        //CsvParser parser = new CsvParser(config);
        //List<Student> st = (List<Student>)(Object)parser.loadEntities(Student.class);
        //System.out.println(st);
    }

    @Test
    public void testStudentsLoadedFromAnnotations() {
        try {
            CsvConfiguration con = CsvConfigurationLoader.loadFromAnnotations(Student.class);
            //CsvParser parser = new CsvParser(con);
            System.out.println(con.getCsvEntities());
            //List<Student> st = (List<Student>)(Object) parser.loadEntities(Student.class);
            //System.out.println(st);
        } catch (MissedAnnotationException missedCsvEntityAnnotation) {
            missedCsvEntityAnnotation.printStackTrace();
        }
    }
}

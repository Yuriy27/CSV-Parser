package com.github.yuriy27.csvparser;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Юра on 02.11.2016.
 */
public class FirstTests {

    @Test
    public void testThreeStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Vladimir", "Putin", "Moscow"));
        students.add(new Student("Petr", "Poroshenko", "Kiev"));
        students.add(new Student("Yuriy", "Kuchevskiy", "Vinnitsa"));
        CsvParser parser = new CsvParser("src/main/resources/test.csv");
       // System.out.println(students);
       // System.out.println(parser.parseCsv());
        //System.out.println(students.equals(parser.parseCsv()));
        assertTrue(students.equals(parser.parseCsv()));
    }
}

package com.github.yuriy27.csvparser;

import com.github.yuriy27.csvparser.config.CsvConfiguration;
import com.github.yuriy27.csvparser.config.FileCsvConfiguration;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Юра on 07.11.2016.
 */
public class ConfigTest {

    @Test
    public void testSeparator() {
        FileCsvConfiguration csv = new FileCsvConfiguration("src/main/resources/config.xml");
        csv.loadConfiguration();
        assertEquals(csv.getSeparator(), ",");
    }

}

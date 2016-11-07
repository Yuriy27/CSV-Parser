package com.github.yuriy27.csvparser.config;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Юра on 07.11.2016.
 */
public class FileCsvConfiguration extends CsvConfigurationImpl {

    private String fpath;

    public FileCsvConfiguration(String fpath) {
        this.fpath = fpath;
    }

    @Override
    public void loadConfiguration() {
        Document doc = null;
        try {
            doc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new File(fpath));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();

        Node sep = doc.getElementsByTagName("separator").item(0);
        if (sep != null) {
            separator = sep.getTextContent();
        }


    }
}

package com.github.yuriy27.csvparser.config;

import com.github.yuriy27.csvparser.entity.CsvEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        csvEntities = new ArrayList();
        NodeList entities = doc.getElementsByTagName("entity");
        for (int i = 0; i < entities.getLength(); i++) {
            CsvEntity temp = new CsvEntity();
            Node node = entities.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                temp.setEclass(elem.getAttribute("class"));
                temp.setResource(elem.getAttribute("resource"));
                NodeList cols = ((Element) node).getElementsByTagName("col");
                int size = cols.getLength();
                String[] type = new String[size];
                String[] fields = new String[size];
                int[] num = new int[size];
                for (int k = 0; k < size; k++) {
                    Node colNode = cols.item(k);
                    if (colNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element colElem = (Element) colNode;
                        type[k] = colElem.getAttribute("type");
                        fields[k] = colElem.getTextContent();
                        num[k] = Integer.parseInt(colElem.getAttribute("num"));
                    }
                }
                temp.setType(type);
                temp.setFields(fields);
                temp.setNum(num);
            }
            csvEntities.add(temp);
        }
    }
}

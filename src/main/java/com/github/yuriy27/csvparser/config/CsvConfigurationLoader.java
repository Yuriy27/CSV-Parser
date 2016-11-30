package com.github.yuriy27.csvparser.config;

import com.github.yuriy27.csvparser.annot.Column;
import com.github.yuriy27.csvparser.annot.CsvEntity;
import com.github.yuriy27.csvparser.entity.CsvModel;
import com.github.yuriy27.csvparser.exception.MissedAnnotationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CsvConfigurationLoader {

    public static CsvConfiguration load(String path) {
        CsvConfiguration result = new CsvConfiguration();
        Document doc = null;
        try {
            doc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new File(path));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();

        Node sep = doc.getElementsByTagName("separator").item(0);

        Map<String, CsvModel> csvEntities = new HashMap<>();
        String eclass = null;
        NodeList entities = doc.getElementsByTagName("entity");
        for (int i = 0; i < entities.getLength(); i++) {
            CsvModel temp = new CsvModel();
            Node node = entities.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                eclass = elem.getAttribute("class");
                temp.setEclass(eclass);
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
            csvEntities.put(eclass, temp);
        }
        result.setCsvEntities(csvEntities);

        return result;
    }

    public static CsvConfiguration loadFromAnnotations(Class<?> clazz)
            throws MissedAnnotationException {
        CsvConfiguration result = new CsvConfiguration();
        CsvModel model = new CsvModel();
        String[] fields, type;
        int[] num;
        //String resource, eclass;
        if (clazz.isAnnotationPresent(CsvEntity.class)) {
            CsvEntity entity = clazz.getAnnotation(CsvEntity.class);
            model.setResource(entity.resource());
            model.setEclass(clazz.getName());
            Field[] declaredFields = clazz.getDeclaredFields();
            int size = declaredFields.length;
            fields = new String[size];
            type = new String[size];
            num = new int[size];
            int ind = 0;
            for (Field field : declaredFields) {
                fields[ind] = field.getName();
                type[ind] = field.getType().getCanonicalName();
                Column column = field.getAnnotation(Column.class);
                num[ind] = column.num();
                ind++;
            }
            model.setNum(num);
            model.setType(type);
            model.setFields(fields);
        } else {
            throw new MissedAnnotationException("@CsvEntity missed..");
        }
        Map<String, CsvModel> map = new HashMap<>();
        map.put(clazz.getName(), model);
        result.setCsvEntities(map);

        return result;
    }

}

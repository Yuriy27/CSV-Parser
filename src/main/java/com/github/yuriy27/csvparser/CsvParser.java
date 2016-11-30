package com.github.yuriy27.csvparser;

import com.github.yuriy27.csvparser.annot.CsvEntity;
import com.github.yuriy27.csvparser.config.CsvConfiguration;
import com.github.yuriy27.csvparser.entity.CsvModel;
import com.github.yuriy27.csvparser.exception.NotSupportedTypeException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    private CsvConfiguration config;

    public CsvConfiguration getConfig() {
        return config;
    }

    public void setConfig(CsvConfiguration config) {
        this.config = config;
    }

    public CsvParser(CsvConfiguration config) {
        this.config = config;
    }

    public List<Object> loadEntities(final Class<?> clazz) {
        final List<Object> result = new ArrayList<>();
        DataLoader dataLoader = new CsvDataLoader();
        List<List<String>> data = dataLoader.load(config.getCsvEntities()
                .get(clazz.getName()).getResource());
        data.forEach(elem-> result.add(getEntity(elem, clazz)));

        return result;
    }

    private Object getEntity(List<String> data, Class<?> clazz) {
        CsvModel entity = config.getCsvEntities().get(clazz.getName());
        Object obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        String[] type = entity.getType();
        String[] fields = entity.getFields();
        int[] num = entity.getNum();
        for (int i = 0; i < fields.length; i++) {
            Field field = null;
            try {
                field = clazz.getDeclaredField(fields[i]);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            field.setAccessible(true);
            try {
                setField(obj, field, type[i], data.get(num[i] - 1));
            } catch (IllegalAccessException | ClassNotFoundException | InstantiationException | NotSupportedTypeException e) {
                e.printStackTrace();
            }
        }

        return obj;
    }

    private Object setField(Object obj, Field field, String type, String data) throws ClassNotFoundException,
            IllegalAccessException,
            InstantiationException,
            NotSupportedTypeException {
        switch (type) {
            case "java.lang.Byte" : field.setByte(obj, Byte.parseByte(data)); break;
            case "java.lang.Short" : field.setShort(obj, Short.parseShort(data)); break;
            case "java.lang.Integer" : field.setInt(obj, Integer.parseInt(data)); break;
            case "java.lang.Long" : field.setLong(obj, Long.parseLong(data)); break;
            case "java.lang.Float" : field.setFloat(obj, Float.parseFloat(data)); break;
            case "java.lang.Double" : field.setDouble(obj, Double.parseDouble(data)); break;
            case "java.lang.Character" : field.setChar(obj, data.charAt(0)); break;
            case "java.lang.Boolean" : field.setBoolean(obj, Boolean.parseBoolean(data)); break;
            case "java.lang.String" : field.set(obj, data); break;
            default: throw new NotSupportedTypeException("CsvParser doesn't support '"
                    + type +"' type");
        }

        return obj;
    }

}

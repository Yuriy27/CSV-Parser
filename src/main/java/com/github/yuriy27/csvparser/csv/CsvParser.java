package com.github.yuriy27.csvparser.csv;

import com.github.yuriy27.csvparser.config.CsvConfiguration;
import com.github.yuriy27.csvparser.entity.CsvModel;
import com.github.yuriy27.csvparser.exception.NotSupportedTypeException;

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

    public List<Object> loadEntities() {
        return config.getDefaultClass() == null ? null : loadEntities(config.getDefaultClass());
    }

    public List<Object> loadEntities(final Class<? extends Object> clazz) {
        final List<Object> result = new ArrayList<>();
        DataLoader dataLoader = new CsvDataLoader();
        List<List<String>> data = dataLoader.load(config.getCsvEntities()
                .get(clazz.getName()).getResource());
        data.forEach(elem-> result.add(getEntity(elem, clazz)));

        return result;
    }

    private Object getEntity(List<String> data, Class<? extends Object> clazz) {
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
            case "byte" : field.setByte(obj, Byte.parseByte(data)); break;
            case "short" : field.setShort(obj, Short.parseShort(data)); break;
            case "int" : field.setInt(obj, Integer.parseInt(data)); break;
            case "long" : field.setLong(obj, Long.parseLong(data)); break;
            case "float" : field.setFloat(obj, Float.parseFloat(data)); break;
            case "double" : field.setDouble(obj, Double.parseDouble(data)); break;
            case "char" : field.setChar(obj, data.charAt(0)); break;
            case "boolean" : field.setBoolean(obj, Boolean.parseBoolean(data)); break;
            case "String" : field.set(obj, data); break;
            default: throw new NotSupportedTypeException("CsvParser doesn't support '"
                    + type +"' type");
        }

        return obj;
    }

}

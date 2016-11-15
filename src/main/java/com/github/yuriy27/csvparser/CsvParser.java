package com.github.yuriy27.csvparser;

import com.github.yuriy27.csvparser.config.CsvConfiguration;
import com.github.yuriy27.csvparser.entity.CsvEntity;
import com.github.yuriy27.csvparser.exception.NotSupportedTypeException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Юра on 02.11.2016.
 */
public class CsvParser {

    //private String path;
    //private String separator = ",";

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

    public List<Object> loadEntities(Class<?> clazz) {
        List<Object> result = new ArrayList<>();
        String separator = config.getSeparator();
        try(BufferedReader reader = new BufferedReader(new FileReader(config.getEntities()
                .get(clazz.getName()).getResource()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] data = line.split(separator);
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].trim();
                }
                result.add(getEntity(data, clazz));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private Object getEntity(String[] data, Class<?> clazz) {
        CsvEntity entity = config.getEntities().get(clazz.getName());
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
                //PROBLEMS HERE
               // field.set(obj, data[num[i] - 1]);
                setField(obj, field, type[i], data[num[i] - 1]);

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

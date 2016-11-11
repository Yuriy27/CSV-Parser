package com.github.yuriy27.csvparser;

import com.github.yuriy27.csvparser.config.CsvConfiguration;
import com.github.yuriy27.csvparser.config.CsvConfigurationImpl;
import com.github.yuriy27.csvparser.entity.CsvEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Юра on 02.11.2016.
 */
public class CsvParser implements IParser {

    //private String path;
    //private String separator = ",";

    private CsvConfigurationImpl config;

    public CsvConfigurationImpl getConfig() {
        return config;
    }

    public void setConfig(CsvConfigurationImpl config) {
        this.config = config;
    }

    public CsvParser(CsvConfigurationImpl config) {
        this.config = config;
    }


  /*  public List<Student> parse() {
        List<Student> result = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                String[] data = line.split(separator);
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].trim();
                }
                Student student = new Student(data[0], data[1], data[2]);
                result.add(student);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        return result;
    }*/

    @Override
    public List<? extends Object> loadEntities(Class<?> clazz) {
        List<? extends Object> result = new ArrayList<>();


        return null;
    }

    private Object getEntity(String[] data, Class<?> clazz) {
        CsvEntity entity = config.getEntities().get(clazz.getName());
        //  System.out.println("______________");
        //  System.out.println(entity);
        //  System.out.println("______________");
        Object obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        // System.out.println("______________");
        //System.out.println(obj);
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
          //  field.set();
        }

        return obj;
    }

}

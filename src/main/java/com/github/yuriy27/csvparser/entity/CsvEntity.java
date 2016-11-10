package com.github.yuriy27.csvparser.entity;

import java.util.Arrays;

/**
 * Created by Юра on 07.11.2016.
 */
public class CsvEntity {

    private String eclass;
    private String resource;
    private String[] type;
    private String[] fields;
    private int[] num;

    public CsvEntity() {
    }

    public CsvEntity(String eclass, String resource, String[] type, String[] fields, int[] num) {
        this.eclass = eclass;
        this.resource = resource;
        this.type = type;
        this.fields = fields;
        this.num = num;
    }

    public String getEclass() {
        return eclass;
    }

    public void setEclass(String eclass) {
        this.eclass = eclass;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public int[] getNum() {
        return num;
    }

    public void setNum(int[] num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "CsvEntity{" +
                "eclass='" + eclass + '\'' +
                ", resource='" + resource + '\'' +
                ", type=" + Arrays.toString(type) +
                ", fields=" + Arrays.toString(fields) +
                ", num=" + Arrays.toString(num) +
                '}';
    }
}

package com.github.yuriy27.csvparser.entity;

/**
 * Created by Юра on 07.11.2016.
 */
public class CsvEntity {

    private String eclass;
    private String resource;
    private String[] type;
    private String[] fields;

    public CsvEntity() {
    }

    public CsvEntity(String eclass, String resource, String[] type, String[] fields) {
        this.eclass = eclass;
        this.resource = resource;
        this.type = type;
        this.fields = fields;
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
}

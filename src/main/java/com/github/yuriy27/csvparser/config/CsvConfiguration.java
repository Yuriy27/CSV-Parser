package com.github.yuriy27.csvparser.config;

import com.github.yuriy27.csvparser.entity.CsvModel;

import java.util.Map;

public class CsvConfiguration {

    private Class<? extends Object> defaultClass = null;

    private Map<String, CsvModel> csvEntities;

    public Map<String, CsvModel> getCsvEntities() {
        return csvEntities;
    }

    public void setCsvEntities(Map<String, CsvModel> csvEntities) {
        this.csvEntities = csvEntities;
    }

    public Class<? extends Object> getDefaultClass() {
        return defaultClass;
    }

    public void setDefaultClass(Class<? extends Object> defaultClass) {
        this.defaultClass = defaultClass;
    }
}

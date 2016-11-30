package com.github.yuriy27.csvparser.config;

import com.github.yuriy27.csvparser.entity.CsvModel;

import java.util.Map;

public class CsvConfiguration {

    private Map<String, CsvModel> csvEntities;

    public Map<String, CsvModel> getCsvEntities() {
        return csvEntities;
    }

    public void setCsvEntities(Map<String, CsvModel> csvEntities) {
        this.csvEntities = csvEntities;
    }

}

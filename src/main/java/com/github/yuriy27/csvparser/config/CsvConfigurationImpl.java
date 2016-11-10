package com.github.yuriy27.csvparser.config;

import com.github.yuriy27.csvparser.entity.CsvEntity;

import java.util.List;

/**
 * Created by Юра on 07.11.2016.
 */
abstract class CsvConfigurationImpl implements CsvConfiguration{

    protected String separator = ",";
    protected List<CsvEntity> csvEntities;

    public String getSeparator() {
        return separator;
    }

    public List<CsvEntity> getEntities() {
        return csvEntities;
    }

    @Override
    public void loadConfiguration() {

    }

}

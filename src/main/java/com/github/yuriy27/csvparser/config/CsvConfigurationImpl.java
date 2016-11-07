package com.github.yuriy27.csvparser.config;

import com.github.yuriy27.csvparser.entity.CsvEntity;

/**
 * Created by Юра on 07.11.2016.
 */
abstract class CsvConfigurationImpl implements CsvConfiguration{

    protected String separator = ",";
    protected CsvEntity[] entities;

    public String getSeparator() {
        return separator;
    }

    public CsvEntity[] getEntities() {
        return entities;
    }

    @Override
    public void loadConfiguration() {

    }

}

package com.github.yuriy27.csvparser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Юра on 11.11.2016.
 */
public interface IParser {

    public List<? extends Object> loadEntities(Class<?> clazz);

}

package com.github.yuriy27.csvparser.csv;

import java.util.List;

public interface DataLoader {

    List<List<String>> load(String path);

}

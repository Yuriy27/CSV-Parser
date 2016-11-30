package com.github.yuriy27.csvparser;

import java.util.List;

public interface DataLoader {

    List<List<String>> load(String path);

}

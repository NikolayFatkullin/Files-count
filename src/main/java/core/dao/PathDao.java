package core.dao;

import java.io.File;
import java.util.Map;

public interface PathDao {
    void add(File path, int amount);

    int get(File path);

    Map<File, Integer> getAll();
}

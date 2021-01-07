package core.dao;

import core.db.Storage;
import java.io.File;
import java.util.Map;
import java.util.Optional;

public class PathDaoImpl implements PathDao {
    @Override
    public void add(File path, int amount) {
        Storage.files.put(path, amount);
    }

    @Override
    public int get(File path) {
        Optional<Integer> optionalInteger = Optional.ofNullable(Storage.files.get(path));
        return optionalInteger.orElseThrow(RuntimeException::new);
    }

    @Override
    public Map<File, Integer> getAll() {
        return Storage.files;
    }
}

package core.service;

import core.dao.PathDao;
import core.dao.PathDaoImpl;
import java.util.stream.Collectors;

public class PathServiceImpl implements PathService {
    private final PathDao pathDao = new PathDaoImpl();

    @Override
    public String generateReport() {
        return pathDao.getAll().entrySet().stream()
                .map(s -> s.getKey() + ";" + s.getValue())
                .collect(Collectors.joining(System.lineSeparator()));

    }
}

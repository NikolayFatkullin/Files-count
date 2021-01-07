package core.service;

import core.dao.PathDao;
import core.dao.PathDaoImpl;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CsvFileReaderService implements FileReaderService {
    private final PathDao pathDao;

    public CsvFileReaderService() {
        this.pathDao = new PathDaoImpl();
    }

    @Override
    public void read(File fileName) {
        try (BufferedReader readFile = new BufferedReader(new FileReader(fileName))) {
            readFile.readLine();
            String lineText;
            while ((lineText = readFile.readLine()) != null) {
                pathDao.add(new File(lineText), 0);
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found - " + fileName, e);
        }
    }
}

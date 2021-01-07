package core;

import core.controller.ConsoleHandler;
import core.controller.KeyListener;
import core.dao.PathDao;
import core.dao.PathDaoImpl;
import core.model.Action;
import core.service.CountFiles;
import core.service.CsvFileReaderService;
import core.service.CsvFileWriterServiceImpl;
import core.service.FileReaderService;
import core.service.FileWriterService;
import core.service.PathService;
import core.service.PathServiceImpl;
import java.io.File;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        FileReaderService fileReaderService = new CsvFileReaderService();
        Action action = consoleHandler.handle();
        fileReaderService.read(action.getPathToRead());
        PathDao pathDao = new PathDaoImpl();
        Map<File, Integer> mapa = pathDao.getAll();
        PathService pathService = new PathServiceImpl();
        AtomicInteger numberOfPath = new AtomicInteger();
        Thread thread = new Thread(new KeyListener());
        thread.start();
        for (Map.Entry<File, Integer> files : mapa.entrySet()) {
            CountFiles countFiles = new CountFiles(files.getKey(), numberOfPath);
            countFiles.run();
            numberOfPath.getAndIncrement();
        }
        FileWriterService fileWriterService = new CsvFileWriterServiceImpl();
        fileWriterService.write(pathService.generateReport(), action.getPathToWrite());
    }
}

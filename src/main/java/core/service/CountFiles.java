package core.service;

import core.dao.PathDao;
import core.dao.PathDaoImpl;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

public class CountFiles implements Runnable {
    private static boolean interrupt = false;
    private final AtomicInteger countFiles = new AtomicInteger();
    private final AtomicInteger count;
    private final PathDao pathDao = new PathDaoImpl();
    private final File pathName;

    public CountFiles(File pathName, AtomicInteger numberOfPath) {
        this.pathName = pathName;
        this.count = numberOfPath;
    }

    public static boolean isInterrupt() {
        return interrupt;
    }

    public static void setInterrupt(boolean interrupt) {
        CountFiles.interrupt = interrupt;
    }

    private void searchFiles(File nameFolder) {
        if (!nameFolder.exists()) {
            throw new RuntimeException("This file is not exists: " + nameFolder);
        }
        if (!nameFolder.isDirectory()) {
            throw new RuntimeException("This file is not a directory: " + nameFolder);
        }
        File[] file = nameFolder.listFiles(File::isFile);
        File[] folder = nameFolder.listFiles(File::isDirectory);
        if (file != null) {
            countFiles.getAndAdd(file.length);
            this.pathDao.add(pathName, countFiles.get());
        }
        if (folder != null) {
            for (File file2 : folder) {
                if (isInterrupt()) {
                    return;
                }
                searchFiles(file2);
            }
        }
    }

    @Override
    public void run() {
        searchFiles(pathName);
        System.out.println(count + " " + pathName + " - " + countFiles);
    }
}




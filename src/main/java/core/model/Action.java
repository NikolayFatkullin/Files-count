package core.model;

import java.io.File;

public class Action {
    private final File pathToRead;
    private final File pathToWrite;

    public Action(File pathToRead, File pathToWrite) {
        this.pathToRead = pathToRead;
        this.pathToWrite = pathToWrite;
    }

    public File getPathToRead() {
        return pathToRead;
    }

    public File getPathToWrite() {
        return pathToWrite;
    }
}

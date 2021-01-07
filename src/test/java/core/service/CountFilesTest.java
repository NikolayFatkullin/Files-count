package core.service;

import static org.junit.Assert.assertEquals;

import core.db.Storage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CountFilesTest {
    private static CountFiles countFiles;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test(expected = RuntimeException.class)
    public void testForNotExistFile() {
        countFiles = new CountFiles(new File("1234"), new AtomicInteger(0));
        countFiles.run();
    }

    @Test(expected = RuntimeException.class)
    public void testForNotExistDirectory() {
        countFiles = new CountFiles(new File("src/test/resources/MyPath.csv"),
                new AtomicInteger(0));
        countFiles.run();
    }

    @Test
    public void testForCorrectData() {
        countFiles = new CountFiles(new File("src/test"), new AtomicInteger(0));
        countFiles.run();
        Map<File, Integer> expected = new LinkedHashMap<>();
        expected.put(new File("src" + File.separator + "test"), 11);
        Map<File, Integer> actual = Storage.files;
        assertEquals("0 src" + File.separator + "test - 11", output.toString().trim());
        assertEquals(expected, actual);
    }

    @After
    public void afterAll() {
        Storage.files.clear();
    }
}

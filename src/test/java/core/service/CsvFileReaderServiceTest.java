package core.service;

import static org.junit.Assert.assertEquals;

import core.db.Storage;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CsvFileReaderServiceTest {
    private static FileReaderService fileReaderService;

    @Before
    public void beforeClass() {
        fileReaderService = new CsvFileReaderService();
    }

    @Test
    public void testForCorrectData() {
        fileReaderService.read(new File("src/test/resources/FirstExample.csv"));
        Map<File, Integer> expected = new LinkedHashMap<>();
        expected.put(new File("D:\\\\flutter"), 0);
        expected.put(new File("C:\\Users\\achil\\IdeaProjects"), 0);
        Map<File, Integer> actual = Storage.files;
        assertEquals(expected, actual);
    }

    @Test
    public void testForCorrectDataSecond() {
        fileReaderService.read(new File("src/test/resources/SecondExample.csv"));
        Map<File, Integer> expected = new LinkedHashMap<>();
        expected.put(new File("src/test"), 0);
        expected.put(new File("src/main/java"), 0);
        expected.put(new File("src"), 0);
        Map<File, Integer> actual = Storage.files;
        assertEquals(expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void testForIncorrectFile() {
        fileReaderService.read(new File(""));
    }

    @After
    public void afterAll() {
        Storage.files.clear();
    }
}

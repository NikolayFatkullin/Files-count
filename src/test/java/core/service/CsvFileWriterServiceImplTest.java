package core.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.Before;
import org.junit.Test;

public class CsvFileWriterServiceImplTest {
    private static FileWriterService fileWriterService;

    @Before
    public void beforeClass() {
        fileWriterService = new CsvFileWriterServiceImpl();
    }

    @Test
    public void testForCorrectData() {
        String expected = "path,count" + System.lineSeparator() + "This is a simple test";
        fileWriterService.write("This is a simple test", new File(
                "src/test/resources/MyPath.csv"));
        String actual = readFromFile("src/test/resources/MyPath.csv");
        assertEquals(expected, actual);
    }

    @Test
    public void testForCorrectDataSecond() {
        String expected = "path,count" + System.lineSeparator() + "Hello World!";
        fileWriterService.write("Hello World!", new File(
                "src/test/resources/MyPathSecond.csv"));
        String actual = readFromFile("src/test/resources/MyPathSecond.csv");
        assertEquals(expected, actual);
    }

    @Test(expected = RuntimeException.class)
    public void testIncorrectFileName() {
        fileWriterService.write("Hello", new File(""));
    }

    private String readFromFile(String fileName) {
        try {
            return Files.readString(Path.of(fileName)).trim();
        } catch (IOException e) {
            throw new RuntimeException("Can't correctly read data from file " + fileName, e);
        }
    }
}

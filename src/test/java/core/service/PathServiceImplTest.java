package core.service;

import static org.junit.Assert.assertEquals;

import core.db.Storage;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PathServiceImplTest {
    private static PathService pathService;

    @Before
    public void beforeClass() {
        pathService = new PathServiceImpl();
        Storage.files.put(new File("D:\\"), 150);
        Storage.files.put(new File("src/main/java/core/service"), 150);
        Storage.files.put(new File("src/main/java/core/model"), 220);
        Storage.files.put(new File("src/main/java/core/db"), 230);
        Storage.files.put(new File("src/main/java/core/dao"), 420);
    }

    @Test
    public void testForWork() {
        String firstExpected = "D:\\;150" + System.lineSeparator()
                + "src\\main\\java\\core\\service;150" + System.lineSeparator()
                + "src\\main\\java\\core\\model;220" + System.lineSeparator()
                + "src\\main\\java\\core\\db;230" + System.lineSeparator()
                + "src\\main\\java\\core\\dao;420";
        String firstActual = pathService.generateReport();
        assertEquals(firstExpected, firstActual);
        Storage.files.put(new File("D:\\"), 0);
        String secondExpected = "D:\\;0" + System.lineSeparator()
                + "src\\main\\java\\core\\service;150" + System.lineSeparator()
                + "src\\main\\java\\core\\model;220" + System.lineSeparator()
                + "src\\main\\java\\core\\db;230" + System.lineSeparator()
                + "src\\main\\java\\core\\dao;420";
        String secondActual = pathService.generateReport();
        assertEquals(secondExpected, secondActual);
    }

    @After
    public void afterAll() {
        Storage.files.clear();
    }
}

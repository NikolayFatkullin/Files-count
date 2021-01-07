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
        Storage.files.put(new File("first"), 150);
        Storage.files.put(new File("second"), 150);
        Storage.files.put(new File("third"), 220);
        Storage.files.put(new File("fourth"), 230);
        Storage.files.put(new File("fifth"), 420);
    }

    @Test
    public void testForWork() {
        String firstExpected = "first;150" + System.lineSeparator()
                + "second;150" + System.lineSeparator()
                + "third;220" + System.lineSeparator()
                + "fourth;230" + System.lineSeparator()
                + "fifth;420";
        String firstActual = pathService.generateReport();
        assertEquals(firstExpected, firstActual);
        Storage.files.put(new File("first"), 0);
        String secondExpected = "first;0" + System.lineSeparator()
                + "second;150" + System.lineSeparator()
                + "third;220" + System.lineSeparator()
                + "fourth;230" + System.lineSeparator()
                + "fifth;420";
        String secondActual = pathService.generateReport();
        assertEquals(secondExpected, secondActual);
    }

    @After
    public void afterAll() {
        Storage.files.clear();
    }
}

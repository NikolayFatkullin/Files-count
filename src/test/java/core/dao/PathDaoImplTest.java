package core.dao;

import static org.junit.Assert.assertEquals;

import core.db.Storage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PathDaoImplTest {
    private static PathDao pathDao;

    @Before
    public void beforeAll() {
        pathDao = new PathDaoImpl();
        Storage.files.put(new File("D:\\\\"), 150);
        Storage.files.put(new File("C:\\"), 150);
        Storage.files.put(new File("target"), 220);
        Storage.files.put(new File("src/test/java/core"), 230);
        Storage.files.put(new File("src/main"), 420);
    }

    @Test
    public void testForAdd() {
        pathDao.add(new File("src/main/java/core/service"), 20);
        int firstExpected = 6;
        int firstActual = Storage.files.size();
        assertEquals(firstExpected, firstActual);
        pathDao.add(new File("src/main"), 40);
        int secondExpected = 6;
        int secondActual = Storage.files.size();
        assertEquals(secondExpected, secondActual);
    }

    @Test
    public void testForGet() {
        int expected = 150;
        int actual = pathDao.get(new File("D:\\\\"));
        assertEquals(expected, actual);

    }

    @Test
    public void testForGetAll() {
        Map<File, Integer> firstExpected = new HashMap<>();
        firstExpected.put(new File("D:\\\\"), 150);
        firstExpected.put(new File("C:\\"), 150);
        firstExpected.put(new File("target"), 220);
        firstExpected.put(new File("src/test/java/core"), 230);
        firstExpected.put(new File("src/main"), 420);
        Map<File, Integer> firstActual = pathDao.getAll();
        assertEquals(firstExpected, firstActual);
    }

    @Test(expected = RuntimeException.class)
    public void testForIncorrectData() {
        pathDao.get(new File("java"));
    }

    @After
    public void afterAll() {
        Storage.files.clear();
    }
}

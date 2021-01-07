package core.controller;

import static org.junit.Assert.assertEquals;

import core.model.Action;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;

public class ConsoleHandlerTest {
    private static ConsoleHandler consoleHandler;

    @Before
    public void beforeAll() {
        consoleHandler = new ConsoleHandler();
    }

    @Test
    public void testForInput() {
        Action expected = new Action(new File("54"), new File("26"));
        InputStream in = new ByteArrayInputStream("54 26".getBytes());
        System.setIn(in);
        assertEquals(expected.getPathToRead(), consoleHandler.handle().getPathToRead());
    }

    @Test
    public void testForInputSecond() {
        Action expected = new Action(new File("54"), new File("26"));
        InputStream in = new ByteArrayInputStream("54 26".getBytes());
        System.setIn(in);
        assertEquals(expected.getPathToWrite(), consoleHandler.handle().getPathToWrite());
    }
}

package core.model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import org.junit.Before;
import org.junit.Test;

public class ActionTest {
    private Action action;

    @Before
    public void beforeAll() {
        action = new Action(new File("FirstExample.csv"), new File("Output.csv"));
    }

    @Test
    public void testForGetRead() {
        File expected = new File("FirstExample.csv");
        assertEquals(expected, action.getPathToRead());
    }

    @Test
    public void testForGetWrite() {
        File expected = new File("Output.csv");
        assertEquals(expected, action.getPathToWrite());
    }
}

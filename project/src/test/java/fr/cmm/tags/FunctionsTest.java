package fr.cmm.tags;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

/**
 * Created by christian on 17/12/15.
 */
public class FunctionsTest {

    @Test
    public void textFormating() throws IOException {
        assertEquals("a", Functions.textFormating("a"));
        assertEquals("a<br>", Functions.textFormating("a\n"));
        assertEquals("&a", Functions.textFormating("&a"));
    }
}

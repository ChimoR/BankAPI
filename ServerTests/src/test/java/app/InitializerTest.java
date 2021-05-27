package app;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class InitializerTest {

    @Test
    public void shouldPreloadCardNumbers() throws IOException {
        assertTrue(Initializer.preloadCardNumbers());
    }

    @Test
    public void shouldInitServer() throws IOException {
        assertTrue(Initializer.initServer());
    }

}
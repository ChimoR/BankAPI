package app;

import org.junit.Test;

import static org.junit.Assert.*;

public class InitializerTest {

    @Test
    public void preloadCardNumbers() {
        assertTrue(Initializer.preloadCardNumbers());
    }

    @Test
    public void initServer() {
        assertTrue(Initializer.initServer());
    }

}
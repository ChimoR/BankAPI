package service;

import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyGeneratorTest {

    @Test
    public void shouldGenerateValidCVV() {
        String expected = PropertyGenerator.generateCardProperties("CVV");
        assertEquals(expected.length(), 3);
        assertTrue(expected.matches("\\d*"));
    }

    @Test
    public void shouldGenerateValidCardNumber() {
        String expected = PropertyGenerator.generateCardProperties("number");
        assertEquals(expected.length(), 10);
        assertTrue(expected.matches("\\d*"));
    }

    @Test
    public void shouldGenerateValidDate() {
        String expected = PropertyGenerator.generateCardProperties("date");
        assertEquals(expected.length(), 10);
        assertTrue(expected.matches("\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d"));
    }
}
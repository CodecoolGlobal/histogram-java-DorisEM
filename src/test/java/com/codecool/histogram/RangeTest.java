package com.codecool.histogram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RangeTest {

    Range range;

    @BeforeEach
    public void setup() {
        range = new Range(2, 5);
    }

    @Test
    public void testIllegalArgumentExceptionInConstructor() {
        assertThrows(IllegalArgumentException.class,
                () -> new Range(-1, 1));

        assertThrows(IllegalArgumentException.class,
                () -> new Range(1, -1));

        assertThrows(IllegalArgumentException.class,
                () -> new Range(2, 1));
    }

    @Test
    public void testIsInRange_testIsWordLengthInRange_returnsTrue() {
        assertTrue(range.isInRange("Ciao"));
    }
    @Test
    public void testIsInRange_wordLengthEqualToParameterFrom_returnsTrue() {
        assertTrue(range.isInRange("Hi"));
    }

    @Test
    public void testIsInRange_wordLengthEqualToParameterTo_returnsTrue() {
        assertTrue(range.isInRange("Hello"));
    }

    @Test
    public void testIsInRange_testWordLengthOutOfRange_returnsFalse() {
        assertFalse(range.isInRange("Codecool"));
    }

    @Test
    public void testToString_correctFormat() {
        assertEquals("2 - 5", range.toString());
    }

}

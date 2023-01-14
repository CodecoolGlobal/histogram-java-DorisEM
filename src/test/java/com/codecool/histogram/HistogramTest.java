package com.codecool.histogram;

import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistogramTest {

    Histogram histogram;

    @BeforeEach
    public void setup() {
        histogram = new Histogram();
    }

    @Test
    public void testNegativeIntegersAsStepParameter() {
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(-3, 4));
    }

    @Test
    public void testNegativeIntegersAsAmountParameter() {
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(3, -4));
    }


}

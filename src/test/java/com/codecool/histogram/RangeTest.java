package com.codecool.histogram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RangeTest {

    Range range;

    @BeforeEach
    public void setup() {
        range = new Range(3, 5);
    }
    
}

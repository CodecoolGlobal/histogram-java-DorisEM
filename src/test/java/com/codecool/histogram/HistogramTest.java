package com.codecool.histogram;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistogramTest {

    Histogram histogram;

    @BeforeEach
    public void setup() {
        histogram = new Histogram();
    }

    @Test
    public void testGenerateRanges_IllegalArgumentException_NegativeIntegerAsStepParameter() {
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(-3, 4));
    }

    @Test
    public void testGenerateRanges_IllegalArgumentException_NegativeIntegerAsAmountParameter() {
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(3, -4));
    }

    @Test
    public void testGenerateRanges_PositiveNumbersAsBothParameters() {
        assertEquals("[1 - 3, 4 - 6, 7 - 9, 10 - 12]", histogram.generateRanges(3, 4).toString());
    }

    @Test
    public void testGenerate_IllegalArgumentException_TextParameterIsNull() {
        assertThrows(IllegalArgumentException.class, () -> histogram.generate(null,
                histogram.generateRanges(3, 4)));
    }

    @Test
    public void testGenerate_IllegalArgumentException_RangesParameterIsNull() {
        assertThrows(IllegalArgumentException.class, () -> histogram.generate("Some text",
                null));
    }

    @Test
    public void testGenerate_TextIsEmpty() {
        String text = "";
        assertEquals("{}", histogram.generate(text, histogram.generateRanges(3, 4)).toString());
    }

    @Test
    public void testGenerate_AllWordsInTextIsOneOfTheGivenRanges() {
        String text = "AB, ABCDE, ABCDEFGH, ABCDEFGHIJK";
        String expected = "{1 - 3=1, 4 - 6=1, 7 - 9=1, 10 - 12=1}";
        assertEquals(expected, histogram.generate(text, histogram.generateRanges(3, 4)).toString());
    }

    @Test void testGenerate_TextWithWordsOutOfGivenRanges() {
        String text = "ABCDEFGHIJKLMNO";
        assertEquals("{}", histogram.generate(text, histogram.generateRanges(3, 4)).toString());
    }

    @Test
    public void testGeneratingHistogramMultipleTimes() {
        String[] texts = {
                "AB, ABCDE, ABCDEFGH, ABCDEFGHIJK",
                "AB, ABC, ABCD, ABCDE, ABCDEFG",
                "AB, ABCDE, ABCDEFGH",
        };

        String[] results = {
                "{1  - 3 =1, 4  - 6 =1, 7  - 9 =1, 10 - 12=1}",
                "{1  - 3 =2, 4  - 6 =2, 7  - 9 =1}",
                "{1  - 3 =1, 4  - 6 =1, 7  - 9 =1}"
        };

        for (int i = 0; i < texts.length; i++) {
            assertEquals(results[i], histogram.generate(texts[i], histogram.generateRanges(3, 4)).toString());
        }
    }

    @Test
    public void testCallingBeforeGeneratingHistogram() {
        String result = "{}";
        assertEquals(result, histogram.getHistogram().toString());
    }

    @Test
    public void testCallingAfterGeneratingHistogram() {
        String text = "AB, ABCDE, ABCDEFGH, ABCDEFGHIJK";
        String result = "{1  - 3 =1, 4  - 6 =1, 7  - 9 =1, 10 - 12=1}";
        histogram.generate(text, histogram.generateRanges(3, 4)).toString();
        assertEquals(result, histogram.getHistogram().toString());
    }

    @Test
    public void testCallingAfterMultipleCallsOfGenerateHistogram() {
        String[] texts = {
                "AB, ABCDE, ABCDEFGH, ABCDEFGHIJK",
                "AB, ABC, ABCD, ABCDE, ABCDEFG",
                "AB, ABCDE, ABCDEFGH",
        };

        String result = "{1  - 3 =1, 4  - 6 =1, 7  - 9 =1}";

        for (String text : texts) {
            histogram.generate(text, histogram.generateRanges(3, 4)).toString();
        }

        assertEquals(result, histogram.getHistogram().toString());
    }

    @Test
    public void testToStringBeforeGeneratingHistogram() {
        String result = "";
        assertEquals(result, histogram.toString());
    }

    @Test
    public void testToStringAfterGeneratingHistogram() {
        String text = "AB, ABCDE, ABCDEFGH, ABCDEFGHIJK";
        String result = (
                """
                1  - 3 | *
                4  - 6 | *
                7  - 9 | *
                10 - 12| *
                """
        );
        histogram.generate(text, histogram.generateRanges(3, 4)).toString();
        assertEquals(result, histogram.toString());
    }

}

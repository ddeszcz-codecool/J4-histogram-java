package com.codecool.histogram;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RangeTest {
    Range range;
    String testString = "test";

    @Test
    public void constructor_fromLessThanZero_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Range(-1, 1));
    }

    @Test
    public void constructor_fromMoreThanTo_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Range(2, 1));
    }

    @Test
    public void isInRange_wordLengthInRange_returnsTrue() {
        range = new Range(testString.length() - 1, testString.length() + 1);
        assertTrue(range.isInRange(testString));
    }

    @Test
    public void isInRange_wordLengthEqualsToRangeFrom_returnsTrue() {
        range = new Range(testString.length(), testString.length() + 1);
        assertTrue(range.isInRange(testString));
    }

    @Test
    public void isInRange_wordLengthEqualsToRangeTo_returnsTrue() {
        range = new Range(testString.length() - 1, testString.length());
        assertTrue(range.isInRange(testString));
    }

    @Test
    public void isInRange_wordLengthOutOfRange_returnsFalse() {
        range = new Range(testString.length() + 1, testString.length() + 2);
        assertFalse(range.isInRange(testString));
    }

    @Test
    public void toString_returnsStringRepresentationOfRange() {
        range = new Range(1, 2);
        assertEquals("1  - 2 ", range.toString());
        range = new Range(5, 10);
        assertEquals("5  - 10", range.toString());
        range = new Range(10, 15);
        assertEquals("10 - 15", range.toString());
        range = new Range(99, 100);
        assertEquals("99 - 100", range.toString());
    }
    
}

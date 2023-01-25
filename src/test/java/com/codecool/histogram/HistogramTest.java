package com.codecool.histogram;

import org.junit.jupiter.api.*;


import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistogramTest {
    Histogram histogram;
    List<Range> ranges;

    @BeforeEach
    void init() {
        histogram = new Histogram();
        ranges = histogram.generateRanges(4, 3);
    }

    //generateRanges------------------------------------------------------
    @Test
    public void generateRanges_positiveParameters_returnsValidRangeList() {
        assertEquals(3, ranges.size());
        assertEquals("1  - 4 ", ranges.get(0).toString());
        assertEquals("5  - 8 ", ranges.get(1).toString());
        assertEquals("9  - 12", ranges.get(2).toString());
    }

    @Test
    public void generateRanges_stepLessThanZero_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(-1, 1));
    }

    @Test
    public void generateRanges_amountLessThanZero_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> histogram.generateRanges(1, -1));
    }

    //generate------------------------------------------------------
    @Test
    public void generate_allWordsInTextIsInOneOfTheGivenRanges_returnsMapWithValueIsTheNumberOfWords() {
        Map<Range, Integer> histogramMap = histogram.generate("seven eight forty", ranges);
        assertEquals(1, histogramMap.size());
        assertNull(histogramMap.get(ranges.get(0)));
        assertEquals(3, histogramMap.get(ranges.get(1)));
        assertNull(histogramMap.get(ranges.get(2)));
    }

    @Test
    public void generate_textWithWordsOutOfGivenRanges_returnsMapWithValueOneForEachRange() {
        Map<Range, Integer> histogramMap = histogram.generate("one eight forty-five", ranges);
        assertEquals(3, histogramMap.size());
        assertEquals(1, histogramMap.get(ranges.get(0)));
        assertEquals(1, histogramMap.get(ranges.get(1)));
        assertEquals(1, histogramMap.get(ranges.get(2)));
    }

    @Test
    public void generate_textParameterIsEmpty_returnsEmptyMap() {
        assertEquals(0, histogram.generate("", ranges).size());
    }

    @Test
    public void generate_textParameterIsNull_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> histogram.generate(null, ranges));
    }

    @Test
    public void generate_rangesParameterIsNull_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> histogram.generate("test", null));
    }

    //getHistogram------------------------------------------------------
    @Test
    public void getHistogram_callingBeforeGeneratingHistogram_returnsEmptyMap() {
        assertEquals(0, histogram.getHistogram().size());
    }

    @Test
    public void getHistogram_callingAfterGeneratingHistogram_returnsHistogramMap() {
        Map<Range, Integer> histogramMap = histogram.generate("seven eight forty", ranges);
        assertEquals(1, histogramMap.size());
        assertEquals(histogramMap, histogram.getHistogram());
    }

    @Test
    public void getHistogram_callingAfterGeneratingMultipleHistograms_returnsLatestMapGenerated() {
        Map<Range, Integer> histogramMap1 = histogram.generate("seven eight forty", ranges);
        Map<Range, Integer> histogramMap2 = histogram.generate("", ranges);
        Map<Range, Integer> histogramMap3 = histogram.generate("one eight forty-five", ranges);
        assertNotEquals(histogramMap1, histogram.getHistogram());
        assertNotEquals(histogramMap2, histogram.getHistogram());
        assertEquals(histogramMap3, histogram.getHistogram());
    }

    //toString------------------------------------------------------
    @Test
    public void toString_callingBeforeGeneratingHistogram_returnsEmptyString() {
        assertEquals("", histogram.toString());
    }

    @Test
    public void toString_callingAfterGeneratingHistogram_returnsStringRepresentationOfHistogram() {
        Map<Range, Integer> histogramMap = histogram.generate("one two three eight forty-five seventy", ranges);

        String histogramString =
                "1  - 4 | **" + System.lineSeparator() +
                        "5  - 8 | ***" + System.lineSeparator() +
                        "9  - 12| *" + System.lineSeparator();

        assertEquals(histogramString, histogram.toString());
    }
}

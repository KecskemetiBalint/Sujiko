package ypa.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ypa.model.Histogram;
import ypa.model.SCell;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@code Histogram}.
 *
 * @author Tom Verhoeff (Eindhoven University of Technology)
 */
public class HistogramTest {

    /** Subject Under Test. */
    private Histogram instance;

    @BeforeEach
    public void setUp() {
        instance = new Histogram();
    }

    /**
     * Checks SUT state to be equal to given array.
     */
    private void checkSUT(final int[] expected) {
        int result;
        for (int state = SCell.BLOCKED; state <= 1; ++state) {
            result = instance.get(state);
            assertEquals(expected[state - SCell.BLOCKED], result, "Count for state " + state);
        }
    }

    /**
     * Tests constructor, of class Histogram.
     */
    @Test
    public void testConstructor() {
        System.out.println("Histogram()");
        checkSUT(new int[3]);
    }

    /**
     * Tests adjust method, of class Histogram.
     */
    @Test
    public void testAdjust() {
        System.out.println("adjust");
        int[] expected = new int[4];
        int delta = 1;
        for (int state = SCell.BLOCKED; state <= 1; ++state) {
            instance.adjust(state, delta);
            expected[state - SCell.BLOCKED] += delta;
            ++delta;
            checkSUT(expected);
        }
    }
    
    @Test
    public void testAdjustWithNegativeDelta() {
        System.out.println("adjust with negative delta");
        int[] expected = new int[4];
        int delta = -1;
        for (int state = SCell.BLOCKED; state <= 1; ++state) {
            instance.adjust(state, delta);
            expected[state - SCell.BLOCKED] += delta;
            ++delta;
            checkSUT(expected);
        }
    }

    @Test
    public void testAdjustWithInvalidState() {
        System.out.println("adjust with invalid state");
        int[] expected = new int[4];
        int invalidState = 100; // assuming valid states are within a certain range
        instance.adjust(invalidState, 1);
        // Expected behavior could be no change or an exception, depending on implementation
        checkSUT(expected);
    }
}

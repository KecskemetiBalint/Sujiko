package ypa.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for {@code SEntry}.
 */
public class SEntryTest {

    /**
     * Test constructor, getDirection, and toString.
     */
    @Test
    public void testConstructor() {
        System.out.println("SEntry");
        String expResult = "17";
        Scanner scanner = new Scanner(expResult);
        SEntry instance = new SEntry(scanner);
        assertAll(
                () -> assertEquals(expResult, instance.toString(), "toString")
        );
    }

    /**
     * Tests scanEntries.
     */
    @Test
    public void testScanEntries() {
        System.out.println("scanEntries, plain");
        String entry0 = "15";
        String entry1 = "16";
        String expResult = entry0 + "\n" + entry1 + "\n";
        List<SEntry> result = SEntry.scanEntries(new Scanner(expResult));
        assertAll(
                () -> assertEquals(2, result.size(), "size"),
                () -> assertEquals(entry0, result.get(0).toString(), "get(0)"),
                () -> assertEquals(entry1, result.get(1).toString(), "get(1)")
        );
    }

    /**
     * Tests scanEntries.
     */
    @Test
    public void testScanEntries2() {
        System.out.println("scanEntries, with extra line");
        String entry0 = "15";
        String entry1 = "17";
        String expResult = entry0 + "\n" + entry1 + "\n=\n";
        Scanner scanner = new Scanner(expResult);
        List<SEntry> result = SEntry.scanEntries(scanner);
        assertAll(
                () -> assertEquals(2, result.size(), "size"),
                () -> assertEquals(entry0, result.get(0).toString(), "get(0)"),
                () -> assertEquals(entry1, result.get(1).toString(), "get(1)"),
                () -> assertTrue(scanner.hasNext("="), "next")
        );
    }

    /**
     * Test isValid().
     */
    @Test
    public void testIsValidAllEmpty() {
        System.out.println("isValid, empty cells");
        String entry = "9";
        Scanner scanner = new Scanner(entry);
        SEntry instance = new SEntry(scanner);
        SCell[] cells = new SCell[] {
            new SCell(SCell.EMPTY),
            new SCell(SCell.EMPTY),
            new SCell(SCell.EMPTY)
        };
        for (SCell cell : cells) {
            instance.add(cell);
            cell.add(instance);
        }
        assertTrue(instance.isValid(), "isValid, all 3 empty");
        cells[0].setState(8);
        assertFalse(instance.isValid(), "isValid, 2 empty, too high");
        cells[0].setState(1);
        assertTrue(instance.isValid(), "isValid, 2 empty, not too high");
        cells[1].setState(2);
        assertTrue(instance.isValid(), "isValid, 1 empty, not too high");
        cells[2].setState(6);
        assertTrue(instance.isValid(), "isValid, no empty, sum OK");
        cells[2].setState(7);
        assertFalse(instance.isValid(), "isValid, no empty, sum too high");
        cells[2].setState(5);
        assertFalse(instance.isValid(), "isValid, no empty, sum too low");
    }

    /**
     * Test equals and hashCode.
     */
    @Test
    public void testEqualsAndHashCode() {
        System.out.println("equals and hashCode");
        String entry = "10";
        Scanner scanner1 = new Scanner(entry);
        Scanner scanner2 = new Scanner(entry);
        SEntry instance1 = new SEntry(scanner1);
        SEntry instance2 = new SEntry(scanner2);
        assertEquals(instance1.toString(), instance2.toString(), "equals");     
    }

    /**
     * Test constructor with large numbers.
     */
    @Test
    public void testConstructorWithLargeNumbers() {
        System.out.println("constructor with large numbers");
        String expResult = String.valueOf(Integer.MAX_VALUE);
        Scanner scanner = new Scanner(expResult);
        SEntry instance = new SEntry(scanner);
        assertEquals(expResult, instance.toString(), "toString with large numbers");
    }

    /**
     * Test constructor with empty input.
     */
    @Test
    public void testConstructorWithEmptyInput() {
        System.out.println("constructor with empty input");
        String expResult = "";
        Scanner scanner = new Scanner(expResult);
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            new SEntry(scanner);
        }); 
    }
}
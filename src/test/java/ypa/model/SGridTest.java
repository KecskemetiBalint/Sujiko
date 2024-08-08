package ypa.model;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Smoke tests for class {@code SGrid}.
 *
 * @author Tom Verhoeff (Eindhoven University of Technology)
 */
public class SGridTest {

    /**
     * Tests constructor.
     */
    @Test
    public void testSGrid() {
        System.out.println("SGrid constructor, plain");
        String expResult = """
                17
                18
                19
                20                
                """;
        String expMatrix = """
                 \\ \\ \\ \\ \\
                 \\ \\ . . .
                 \\ . \\ \\ \\
                 \\ . \\ \\ \\
                """;
        final SGrid instance;
        instance = new SGrid(new Scanner(expResult));
        System.out.println(instance);
        System.out.println(instance.gridAsString());
        assertAll(
                () -> assertEquals(3, instance.getRowCount(), "getRowCount"),
                () -> assertEquals(3, instance.getColumnCount(), "getColumnCount")
        );
    }
    
    /**
     * Test constructor with minimal grid size.
     */
    @Test
    public void testSGridMinimalSize() {
        System.out.println("SGrid constructor, minimal size");
        String expResult = """
                10\n10\n10\n10\n
                """;
        SGrid instance = new SGrid(new Scanner(expResult));
        assertAll(
                () -> assertEquals(3, instance.getRowCount(), "getRowCount"),
                () -> assertEquals(3, instance.getColumnCount(), "getColumnCount")
        );
    }
    
    /**
     * Test setting and getting cell values.
     */
    @Test
    public void testSetAndGetCellValue() {
        System.out.println("set and get cell value");
        String expResult = """
                1 2
                3 4
                """;
        SGrid instance = new SGrid(new Scanner(expResult));
        instance.getCell(0, 0).setState(SCell.BLOCKED);
        assertEquals(SCell.BLOCKED, instance.getCell(0, 0).getState(), "set and get cell value");
    }

    /**
     * Test equals and hashCode.
     */
    @Test
    public void testEqualsAndHashCode() {
        System.out.println("equals and hashCode");
        String expResult = """
                1 2
                3 4
                """;
        Scanner scanner1 = new Scanner(expResult);
        Scanner scanner2 = new Scanner(expResult);
        SGrid instance1 = new SGrid(scanner1);
        SGrid instance2 = new SGrid(scanner2);
        assertEquals(instance1.toString(), instance2.toString(), "equals");
    }

    /**
     * Test isFull method.
     */
    @Test
    public void testIsFull() {
        System.out.println("isFull");
        String expResult = """
                1 2 3
                4 5 6
                7 8 9
                """;
        SGrid instance = new SGrid(new Scanner(expResult));
        assertFalse(instance.isFull(), "Grid is not full initially");
        for (int i = 0; i < instance.getRowCount(); i++) {
            for (int j = 0; j < instance.getColumnCount(); j++) {
                instance.getCell(i, j).setState(1);
            }
        }
        assertTrue(instance.isFull(), "Grid is full after setting all cells");
    }

    /**
     * Test isValid method.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        String expResult = """
                10\n10\n10\n10\n
                """;
        SGrid instance = new SGrid(new Scanner(expResult));
        assertTrue(instance.isValid(), "Grid is initially valid");

        instance.getCell(0, 0).setState(5);
        instance.getCell(0, 1).setState(5); // Duplicate value
        assertFalse(instance.isValid(), "Grid is invalid due to duplicate values");
    }

    /**
     * Test clear method.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        String expResult = """
                1 2 3
                4 5 6
                7 8 9
                """;
        SGrid instance = new SGrid(new Scanner(expResult));
        for (int i = 0; i < instance.getRowCount(); i++) {
            for (int j = 0; j < instance.getColumnCount(); j++) {
                instance.getCell(i, j).setState(1);
            }
        }
        instance.clear();
        for (int i = 0; i < instance.getRowCount(); i++) {
            for (int j = 0; j < instance.getColumnCount(); j++) {
                assertEquals(SCell.EMPTY, instance.getCell(i, j).getState(), 
                        "Cell should be empty after clear");
            }
        }
    }
}
package ypa.model;

import org.junit.jupiter.api.Test;

import ypa.model.Location;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test cases for {@code Location}.
 *
 * @author Tom Verhoeff (Eindhoven University of Technology)
 */
public class LocationTest {

    /**
     * Test of getRow method, of class Location.
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        Location instance = new Location(1, 2);
        int expResult = 1;
        int result = instance.getRow();
        assertEquals(expResult, result, "Result");
    }

    /**
     * Test of getColumn method, of class Location.
     */
    @Test
    public void testGetColumn() {
        System.out.println("getColumn");
        Location instance = new Location(1, 2);
        int expResult = 2;
        int result = instance.getColumn();
        assertEquals(expResult, result, "Result");
    }

    /**
     * Test of shortString method, of class Location.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Location instance = new Location(2, 0);
        String expResult = "c 0";
        String result = instance.toString();
        assertEquals(expResult, result, "Result");
    }

    /**
     * Test of toString method, of class Location.
     */
    @Test
    public void testToStringLong() {
        System.out.println("toStringLong");
        Location instance = new Location(1, 2);
        String expResult = "{ row: 1, column: 2 }";
        String result = instance.toStringLong();
        assertEquals(expResult, result, "Result");
    }

    /**
     * Test of Location(Scanner) constructor of class Location.
     */
    @Test
    public void testLocationScanner() {
        System.out.println("Location(Scanner)");
        String expResult = "b 3";
        Scanner scanner = new Scanner(expResult);
        Location instance = new Location(scanner);
        String result = instance.toString();
        assertEquals(expResult, result, "toString");

        expResult = "z10";
        instance = new Location(new Scanner(expResult));
        result = instance.toString();
        assertEquals(expResult, result, "toString");
    }

    @Test
    public void testEqualsAndHashCode() {
        System.out.println("equals and hashCode");
        Location loc1 = new Location(5, 10);
        Location loc2 = new Location(5, 10);
        assertEquals(loc1.toString(), loc2.toString());
    }

    @Test
    public void testBoundaryValuesMax() {
        System.out.println("boundary values max");
        Location locMax = new Location(Integer.MAX_VALUE, Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, locMax.getRow());
        assertEquals(Integer.MAX_VALUE, locMax.getColumn());
    }
        
    /**
     * Test of equals method, of class Location.
     */
    @Test
    public void testEquals() {
        Location instance1 = new Location(1, 2);
        Location instance2 = new Location(1, 2);
        assertEquals(instance1.toString(), instance2.toString(), "equals");
    }

    /**
     * Test of hashCode method, of class Location.
     */
    @Test
    public void testHashCode() {
        Location instance1 = new Location(1, 2);
        Location instance2 = new Location(1, 2);
        assertEquals(instance1.toString(), instance2.toString(), "hashCode");
    }

}
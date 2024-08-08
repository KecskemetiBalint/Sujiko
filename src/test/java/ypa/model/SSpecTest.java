package ypa.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test cases for {@code SSpec}.
 *
 * @author Tom Verhoeff (Eindhoven University of Technology)
 */
public class SSpecTest {

    /**
     * Test of getSum method, of class SSpec.
     */
    @Test
    public void testGetSum() {
        System.out.println("getSum");
        SSpec instance = new SSpec(9, 3);
        int expResult = 9;
        int result = instance.getSum();
        assertEquals(expResult, result, "Result");
    }

    /**
     * Test of getLength method, of class SSpec.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        SSpec instance = new SSpec(9, 3);
        int expResult = 3;
        int result = instance.getLength();
        assertEquals(expResult, result, "Result");
    }

    /**
     * Test of toString method, of class SSpec.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SSpec instance = new SSpec(9, 3);
        String expResult = " 9";
        String result = instance.toString();
        assertEquals(expResult, result, "Result");
    }

    /**
     * Test of toStringLong method, of class SSpec.
     */
    @Test
    public void testToStringLong() {
        System.out.println("toStringLong");
        SSpec instance = new SSpec(9, 3);
        String expResult = "{ sum: 9, length: 3 }";
        String result = instance.toStringLong();
        assertEquals(expResult, result, "Result");
    }

    /**
     * Test of {@code SSpec(Set<Integer>)} constructor of class SSpec.
     */
    @Test
    public void testSSpecSetInteger() {
        System.out.println("SSpec(Set<Integer>)");
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(3);
        set.add(5);
        String expResult = " 9";
        SSpec instance = new SSpec(set);
        String result = instance.toString();
        assertEquals(expResult, result, "toString");
    }

    /**
     * Test of {@code SSpec(Scanner)} constructor of class SSpec.
     */
    @Test
    public void testSSpecScanner() {
        System.out.println("SSpec(Scanner)");
        String expResult = " 9";
        SSpec instance = new SSpec(new Scanner(expResult));
        String result = instance.toString();
        assertEquals(expResult, result, "toString");
    }

    @Test
    public void testEqualityAndHashCode() {
        System.out.println("equals and hashCode");
        SSpec spec1 = new SSpec(9, 3);
        SSpec spec2 = new SSpec(9, 3);
        assertEquals(spec1.toString(), spec2.toString());

    }

    @Test
    public void testEdgeCases() {
        System.out.println("edge cases");
        Set<Integer> emptySet = new HashSet<>();
        SSpec specEmpty = new SSpec(emptySet);
        assertEquals(0, specEmpty.getSum());
        assertEquals(0, specEmpty.getLength());
    }

}
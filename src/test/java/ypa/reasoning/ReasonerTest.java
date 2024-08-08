package ypa.reasoning;

import org.junit.jupiter.api.Test;

import ypa.command.CompoundCommand;
import ypa.model.SPuzzle;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for {@link Reasoner}.
 *
 * @author wstomv
 */
public class ReasonerTest {

    public static final String PUZZLE = "17\n18\n15\n15\n=\nb 1 = 1\nb 2 = 2\nc 2 = 9\n";

    /** Puzzle for testing. */
    private final SPuzzle puzzle;

    public ReasonerTest() {
        puzzle = new SPuzzle(new Scanner(PUZZLE), "Test");
    }

    /**
     * Test of apply method, of class Reasoner.
     */
    @Test
    public void testApply() {
        System.out.println("apply");
        Reasoner instance = new Reasoner(puzzle);
        CompoundCommand result = instance.apply();
        assertAll(
                () -> assertEquals(0, result.size(), "result.size()"),
                () -> assertTrue(result.isExecuted(), "result.executed")
        );
    }

}
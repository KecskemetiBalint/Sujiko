package ypa.reasoning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ypa.command.CompoundCommand;
import ypa.model.SCell;
import ypa.model.SPuzzle;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for {@link FixpointReasoner}.
 *
 * @author wstomv
 */
public class FixpointReasonerTest {

    private SPuzzle puzzle;

    @BeforeEach
    public void setUp() {
        puzzle = new SPuzzle(new Scanner(ReasonerTest.PUZZLE), "Test");
    }

    /**
     * Test of apply method, of class FixpointReasoner.
     */
    @Test
    public void testApplyEmpty() {
        System.out.println("apply empty");
        Reasoner reasoner = new EntryWithOneEmptyCell(puzzle);
        FixpointReasoner instance = new FixpointReasoner(puzzle, reasoner);
        System.out.println(puzzle.gridAsString());
        CompoundCommand result = instance.apply();
        System.out.println(puzzle.gridAsString());
        assertAll(
                () -> assertEquals(1, result.size(), "result.size()"),
                () -> assertTrue(result.isExecuted(), "result.executed"),
                () -> assertFalse(puzzle.isSolved(), "puzzle solved")
        );
    }

    /**
     * Test of apply method, of class FixpointReasoner.
     */
    @Test
    public void testApplySolved() {
        System.out.println("apply solved");
        SCell cell11 = puzzle.getCell(1, 1);
        cell11.setState(1);
        Reasoner reasoner = new EntryWithOneEmptyCell(puzzle);
        FixpointReasoner instance = new FixpointReasoner(puzzle, reasoner);
        System.out.println(puzzle.gridAsString());
        CompoundCommand result = instance.apply();
        System.out.println(puzzle.gridAsString());
        assertAll(
                () -> assertEquals(1, result.size(), "result.size()"),
                () -> assertTrue(result.isExecuted(), "result.executed"),
                () -> assertFalse(puzzle.isSolved(), "puzzle solved")
        );
    }

    /**
     * Test of apply method, of class FixpointReasoner.
     */
    @Test
    public void testApplyUnsolvable1() {
        System.out.println("apply immediately unsolvable");
        SCell cell = puzzle.getCell(2, 1);
        cell.setState(2);
        Reasoner reasoner = new EntryWithOneEmptyCell(puzzle);
        FixpointReasoner instance = new FixpointReasoner(puzzle, reasoner);
        System.out.println(puzzle.gridAsString());
        CompoundCommand result = instance.apply();
        System.out.println(puzzle.gridAsString());
        assertAll(
                () -> assertNotNull(result, "result null"),
                () -> assertFalse(puzzle.isSolved(), "puzzle not solved"),
                () -> assertEquals(5, puzzle.getStateCount(SCell.EMPTY), "puzzle unchanged")
        );
    }

    /**
     * Test of apply method, of class FixpointReasoner.
     */
    @Test
    public void testApplyUnsolvable2() {
        System.out.println("apply indirectly unsolvable");
        SCell cell = puzzle.getCell(1, 2);
        cell.setState(1);
        Reasoner reasoner = new EntryWithOneEmptyCell(puzzle);
        FixpointReasoner instance = new FixpointReasoner(puzzle, reasoner);
        System.out.println(puzzle.gridAsString());
        CompoundCommand result = instance.apply();
        System.out.println(puzzle.gridAsString());
        assertAll(
                () -> assertNull(result, "result null"),
                () -> assertFalse(puzzle.isSolved(), "puzzle not solved"),
                () -> assertEquals(6, puzzle.getStateCount(SCell.EMPTY), "puzzle unchanged")
        );
    }

}
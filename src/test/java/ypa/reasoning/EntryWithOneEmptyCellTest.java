package ypa.reasoning;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ypa.command.CompoundCommand;
import ypa.model.SCell;
import ypa.model.SPuzzle;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for {@link EntryWithOneEmptyCell}.
 *
 * @author wstomv
 */
public class EntryWithOneEmptyCellTest {

    private SPuzzle puzzle;

    /**
     * Prepares each test case.
     */
    @BeforeEach
    public void setUp() {
        puzzle = new SPuzzle(new Scanner(ReasonerTest.PUZZLE), "Test");
        System.out.println(puzzle);
        System.out.println(puzzle.gridAsString());
    }

    /**
     * Test of applyToCell method, of class EntryWithOneEmptyCell.
     */
    @Test
    public void testApplyToCell() {
        System.out.println("applyToCell");
        SCell cell11 = puzzle.getCell(1, 1);
        cell11.setState(1);
        SCell cell21 = puzzle.getCell(2, 1);
        EntryWithOneEmptyCell instance = new EntryWithOneEmptyCell(puzzle);
        System.out.println(puzzle.gridAsString());
        CompoundCommand result = instance.applyToCell(cell21);
        System.out.println(puzzle.gridAsString());
        assertAll(
                () -> assertEquals(1, result.size(), "result.size()"),
                () -> assertFalse(result.isExecuted(), "result.executed"),
                () -> assertEquals(1, cell11.getState(), "cell 1, 1 state"),
                () -> assertEquals(SCell.EMPTY, cell21.getState(), "cell 1, 2 state")
        );
    }

    /**
     * Test of apply method, of class EntryWithOneEmptyCell.
     */
    @Test
    public void testApply() {
        System.out.println("apply");
        SCell cell11 = puzzle.getCell(1, 1);
        cell11.setState(1);
        SCell cell12 = puzzle.getCell(1, 2);
        EntryWithOneEmptyCell instance = new EntryWithOneEmptyCell(puzzle);
        System.out.println(puzzle.gridAsString());
        CompoundCommand result = instance.apply();
        System.out.println(puzzle.gridAsString());
        assertAll(
                () -> assertEquals(1, result.size(), "result.size()"),
                () -> assertTrue(result.isExecuted(), "result.executed"),
                () -> assertEquals(1, cell11.getState(), "cell 1, 1 state"),
                () -> assertEquals(2, cell12.getState(), "new cell 1, 2 state")
        );
    }

}
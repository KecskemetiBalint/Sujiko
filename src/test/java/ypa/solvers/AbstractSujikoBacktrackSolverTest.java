package ypa.solvers;

import ypa.model.SPuzzle;

import org.junit.jupiter.api.BeforeEach;

import java.util.Scanner;

import ypa.reasoning.Reasoner;
import ypa.reasoning.ReasonerTest;

import static org.junit.jupiter.api.Assertions.*;

/** Provides an abstract class for all the solvers in the suite.
 */
public abstract class AbstractSujikoBacktrackSolverTest {
    protected SPuzzle puzzle;

    @BeforeEach
    public void setUp() {
        puzzle = new SPuzzle(new Scanner(ReasonerTest.PUZZLE), "Test");
    }

    /** Callable assertion method for all subclasses.
    */
    protected void assertSolution(SujikoBacktrackSolver instance, boolean expResult, 
            int expectedCommandSize) {
        boolean result = instance.solve();
        assertAll(
            () -> assertEquals(expResult, result, "return value"),
            () -> assertTrue(puzzle.isSolved(), "puzzle solved"),
            () -> assertEquals(expectedCommandSize, instance.getCommands().size(), "commands size")
        );
    }
}

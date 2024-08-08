package ypa.solvers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Tests without any reasoner.
*/
public class SujikoBacktrackSolverWithoutReasonerTest extends AbstractSujikoBacktrackSolverTest {

    @Test
    public void testSolveWithoutReasoner() {
        System.out.println("solve w/o reasoner");
        SujikoBacktrackSolver instance = new SujikoBacktrackSolver(puzzle, null);
        boolean expResult = true;
        System.out.println(puzzle.gridAsString());
        assertSolution(instance, expResult, 6);
    }
}

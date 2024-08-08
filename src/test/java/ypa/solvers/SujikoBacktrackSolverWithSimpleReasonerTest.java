package ypa.solvers;

import org.junit.jupiter.api.Test;

import ypa.reasoning.EntryWithOneEmptyCell;
import ypa.reasoning.Reasoner;

/** Tests with a simple reasoner. 
*/
public class SujikoBacktrackSolverWithSimpleReasonerTest extends AbstractSujikoBacktrackSolverTest {

    @Test
    public void testSolveWithSimpleReasoner() {
        System.out.println("solve with simple reasoner");
        Reasoner reasoner = new EntryWithOneEmptyCell(puzzle);
        SujikoBacktrackSolver instance = new SujikoBacktrackSolver(puzzle, reasoner);
        boolean expResult = true;
        System.out.println(puzzle.gridAsString());
        assertSolution(instance, expResult, 7);
    }
}


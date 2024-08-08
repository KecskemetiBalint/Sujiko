package ypa.solvers;

import org.junit.jupiter.api.Test;

import ypa.reasoning.EntryWithOneEmptyCell;
import ypa.reasoning.FixpointReasoner;
import ypa.reasoning.Reasoner;

/** Tests the fixpoint reasoner.
*/
public class SujikoBacktrackSolverWithFixpointReasonerTest 
        extends AbstractSujikoBacktrackSolverTest {

    @Test
    public void testSolveWithFixpointReasoner() {
        System.out.println("solve with fixpoint");
        Reasoner reasoner = new EntryWithOneEmptyCell(puzzle);
        reasoner = new FixpointReasoner(puzzle, reasoner);
        SujikoBacktrackSolver instance = new SujikoBacktrackSolver(puzzle, reasoner);
        boolean expResult = true;
        System.out.println(puzzle.gridAsString());
        assertSolution(instance, expResult, 5);
    }
}


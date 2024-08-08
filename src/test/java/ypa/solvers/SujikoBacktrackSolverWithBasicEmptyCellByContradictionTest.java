package ypa.solvers;

import org.junit.jupiter.api.Test;

import ypa.reasoning.BasicEmptyCellByContradiction;
import ypa.reasoning.Reasoner;

/** Class to test the basic empty cell by contradiction solver.
*/

public class SujikoBacktrackSolverWithBasicEmptyCellByContradictionTest 
        extends AbstractSujikoBacktrackSolverTest {

    @Test
    public void testSolveWithBasicEmptyCellByContradiction() {
        System.out.println("solve with BasicEmptyCellByContradiction");
        Reasoner reasoner = new BasicEmptyCellByContradiction(puzzle);
        SujikoBacktrackSolver instance = new SujikoBacktrackSolver(puzzle, reasoner);
        boolean expResult = true;
        System.out.println(puzzle.gridAsString());
        assertSolution(instance, expResult, 7);
    }
}


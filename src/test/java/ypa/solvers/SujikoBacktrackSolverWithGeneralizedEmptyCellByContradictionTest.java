package ypa.solvers;

import org.junit.jupiter.api.Test;

import ypa.reasoning.GeneralizedEmptyCellByContradiction;
import ypa.reasoning.Reasoner;

/** Tests the generalised reasoner .
*/
public class SujikoBacktrackSolverWithGeneralizedEmptyCellByContradictionTest 
        extends AbstractSujikoBacktrackSolverTest {

    @Test
    public void testSolveWithGeneralizedEmptyCellByContradiction() {
        System.out.println("solve with GeneralizedEmptyCellByContradiction");
        Reasoner reasoner = new GeneralizedEmptyCellByContradiction(puzzle);
        SujikoBacktrackSolver instance = new SujikoBacktrackSolver(puzzle, reasoner);
        boolean expResult = true;
        System.out.println(puzzle.gridAsString());
        assertSolution(instance, expResult, 5);
    }
}

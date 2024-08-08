package ypa.solvers;

import org.junit.jupiter.api.Test;

import ypa.reasoning.CompoundReasoner;
import ypa.reasoning.EntryWithOneEmptyCell;
import ypa.reasoning.GeneralizedEmptyCellByContradiction;

/** Tests the compound reasoner.
*/
public class SujikoBacktrackSolverWithCompoundReasonerTest 
        extends AbstractSujikoBacktrackSolverTest {

    @Test
    public void testSolveWithCompoundReasoner() {
        System.out.println("solve with compound reasoner");
        CompoundReasoner compoundReasoner = new CompoundReasoner(puzzle);
        compoundReasoner.add(new EntryWithOneEmptyCell(puzzle));
        compoundReasoner.add(new GeneralizedEmptyCellByContradiction(puzzle));
        SujikoBacktrackSolver instance = new SujikoBacktrackSolver(puzzle, compoundReasoner);
        boolean expResult = true;
        System.out.println(puzzle.gridAsString());
        assertSolution(instance, expResult, 5);
    }
}

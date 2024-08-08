package ypa.solvers;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * Test cases for {@link SujikoBacktrackSolver}.
 * 
 * Note that all the expected stack command sizes are temporary
 * 
 * @author 20231942
 */

@Suite
@SelectClasses({
    SujikoBacktrackSolverWithoutReasonerTest.class,
    SujikoBacktrackSolverWithSimpleReasonerTest.class,
    SujikoBacktrackSolverWithFixpointReasonerTest.class,
    SujikoBacktrackSolverWithBasicEmptyCellByContradictionTest.class,
    SujikoBacktrackSolverWithCompoundReasonerTest.class,
    SujikoBacktrackSolverWithGeneralizedEmptyCellByContradictionTest.class
})

public class SujikoBacktrackSolverTest {
}
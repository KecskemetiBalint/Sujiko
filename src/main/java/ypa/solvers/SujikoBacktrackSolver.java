package ypa.solvers;

import ypa.command.Command;
import ypa.command.SetCommand;
import ypa.model.SPuzzle;
/* TODO: Implement SPuzzle model class */
import ypa.model.SCell;
/* TODO: Implement SPuzzle model cell*/
import ypa.reasoning.Reasoner;
/* TODO: Adapt reasoner package */

/**
 * A simple recursive backtracking solver for Sujiko Puzzles.
 * It uses puzzle.getMinNumber() and puzzle.getMaxNumber()
 * to obtain the range of `digits' to try in an empty cell.
 * <p>
 One reasoner strategy can be injected via the constructor.
 If null, it will be ignored; otherwise, it will be invoked
 before looking for an empty cell and trying all possible `digits'.
 <p>
 * It makes sense for client code to supply a fixpoint strategy.
 *
 * @author 20231942
 */

public class SujikoBacktrackSolver extends SujikoAbstractSolver {
    
    /** The strategy to apply before speculating; null if no reasoner. */
    protected Reasoner reasoner;

    /* Rep. invariant:
     *  reasoner != null ==> reasoner.puzzle == this.puzzle
     */

    /**
     * Constructs a backtracking solver for a given puzzle.
     *
     * @param puzzle  the puzzle
     * @throws IllegalArgumentException  if {@code puzzle == null}
     * @pre {@code puzzle != null}
     */
    public SujikoBacktrackSolver(SPuzzle puzzle, final Reasoner reasoner) {
        super(puzzle);
        this.reasoner = reasoner;
    }
    
    // Auxiliary methods
    /**
     * Returns first empty cell, or null if no empty cells.
     *
     * @return first empty cell, or null if no empty cells
     */
    protected SCell getEmptyCell() {
        for (final SCell cell : puzzle.getCells()) {
            if (cell.isEmpty()) {
                return cell;
            }
        }
        return null;
    }
    
    @Override
    public boolean solve() {
        // Backtracking solver, using the reasoner if not null
        if (reasoner != null) {
            // first, fill in cells by reasoning
            final Command compound = reasoner.apply();
            if (compound == null) {
                // not solvable
                return false;
            }
            commands.push(compound);
        }

        final SCell cell = getEmptyCell();
        if (cell == null) {
            // no more empty cells
            return puzzle.isValid();
        } else {
            // cell is empty; set it in all possible ways
            for (int state = puzzle.getMinNumber(); state <= puzzle.getMaxNumber(); ++state) {
                final Command command = new SetCommand(cell, state);
                command.execute();
                if (puzzle.isValid()) {
                    commands.push(command);
                    // number of open cells is one less
                    if (solve()) {
                        return true;
                    }
                    // no solution found
                    commands.pop();
                }
                // restore cell state
                command.revert();
            }
        }
        if (reasoner != null) {
            // to revert the reasoned cells
            commands.pop().revert();
        }

        return false;
    }
}

package ypa.reasoning;

import ypa.command.CompoundCommand;
import ypa.model.SCell;
import ypa.model.SPuzzle;

/**
 * Abstract template class for reasoners based on empty cells,
 * using the Template Method design pattern.
 * The template method is {@code apply()}, and
 * the overridable hook method is {@code applyToCell()}.
 * It repeatedly looks for an empty cell and then applies
 * {@code applyToCell()}.
 * It stops on the first cell where it either finds a forced command
 * or a contradiction.
 * If no such cell is found, then it returns an empty list.
 *
 * @author 20231942
 */
public abstract class EmptyCellReasoner extends Reasoner {

    public EmptyCellReasoner(SPuzzle puzzle) {
        super(puzzle);
    }

    @Override
    public CompoundCommand apply() {
        final CompoundCommand result = super.apply();

// Apply reasoner to all empty cells, execute and return command
        for (SCell cell : puzzle.getCells()) {
            if (cell.isEmpty()) {
                CompoundCommand command = applyToCell(cell);
                if (command == null) {
                    return command;
                } else if (command.size() > 0) {
                    command.execute();
                    return command;
                }
            }
        }
//

        return result;
    }

    /**
     * Hook method to handle a single cell.
     * See {@link Reasoner#apply()} for what it returns when.
     * However, if a command is returned, it will not have been executed.
     *
     * @param cell  the empty cell
     * @return command to apply, or null if not applicable
     * @throws NullPointerException  if {@code cell == null}
     * @pre {@code cell != null && cell.isEmpty()}
     * @post {@code
     *      (\result == null  ==>  puzzle is not solvable and not modified) &&
     *      (\result.size() > 0  ==>  ! \result.isExecuted() && puzzle.isValid())}
     */
    CompoundCommand applyToCell(final SCell cell) throws NullPointerException {
        assert cell.isEmpty() : "cell at location " + cell.getLocation() + " not empty";
        return new CompoundCommand(false);
    }

}

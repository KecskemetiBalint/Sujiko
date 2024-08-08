package ypa.reasoning;

import ypa.command.Command;
import ypa.command.CompoundCommand;
import ypa.command.SetCommand;
import ypa.model.AbstractGroup;
import ypa.model.SCell;
import ypa.model.SEntry;
import ypa.model.SPuzzle;

/**
 * When all cells but one of an entry have been filled,
 * then the last empty cell remaining can be calculated.
 *
 * @author 20231942
 */
public class EntryWithOneEmptyCell extends EmptyCellReasoner {

    public EntryWithOneEmptyCell(SPuzzle puzzle) {
        super(puzzle);
    }

    @Override
    CompoundCommand applyToCell(SCell cell) throws NullPointerException {
        if (!cell.isEmpty()) {
            throw new IllegalArgumentException(this.getClass().getSimpleName()
                    + "applyToCell.pre failed: cell is not empty");
        }
        CompoundCommand result = super.applyToCell(cell);
        
        /* TODO: Edit probably lines 34 ==> 38, based on logic implemented in model package
        */
        
        for (AbstractGroup g : cell.groups()) {
            if (g instanceof SEntry && g.getStateCount(SCell.EMPTY) == 1) {
                // g is a horizontal or vertEntryEmpty ical entry with one empty cell
                int sum = ((SEntry) g).getSpecification().getSum();
                int newState = sum - g.getTotal();
                if (!puzzle.isValidNumber(newState)) {
                    return null;
                }
                final Command command = new SetCommand(cell, newState);
                command.execute();
                final boolean valid = puzzle.isValid();
                command.revert();
                if (valid) {
                    result.add(command);
                    return result;
                } else {
                    return null;
                }
            }
        }

        return result;
    }

}

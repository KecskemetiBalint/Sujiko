package ypa.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Entry in a Sujiko puzzle, consisting of a location, direction,
 * a specification, and a group of cells.
 * The location, direction, and specification are immutable.
 * The group of cells is mutable, and set cell-by-cell during construction.
 *
 * An entry traverses two phases:
 *
 * <ol>
 * <li>Constructor sets location, direction, and specification.
 * <li>During puzzle set-up, cells are associated.
 * <li>During solving, all cells have been associated (see invariant Size),
 *   and should not change any more.
 * </ol>
 *
 * @inv NoBlocked: {@code (\forall cell : this; ! cell.isBlocked)}
 *
 * @inv <br>Size: {@code this.getCount() == this.specification.getLength()}
 *
 * @author Tom Verhoeff (Eindhoven University of Technology)
 */
public class SEntry extends AbstractGroup {
    /** The specification. */
    private final SSpec specification;

    /**
     * Constructs a {@code SEntry} from a given specification.
     *
     * @param specification  the given specification
     */
    public SEntry(final SSpec specification) {
        this.specification = specification;
    }

    /**
     * Constructs a {@code SEntry} from a given scanner.
     *
     * @param scanner  the given scanner
     */
    public SEntry(final Scanner scanner) {
        this.specification = new SSpec(scanner);
    }

    public SSpec getSpecification() {
        return specification;
    }

    /**
     * Adds a given empty cell.
     *
     * @param cell  the cell to add
     * @pre {@code cell.isEmpty()}
     */
    @Override
    public void add(final SCell cell) {
        if (!cell.isEmpty()) {
            throw new IllegalArgumentException(getClass().getSimpleName()
                    + "add().pre failed: cell is not empty");
        }
        super.add(cell);
    }

    @Override
    public boolean isValid() {
        for (SCell cell : this) {
            // these cells are not blocked
            if (!cell.isEmpty() && this.getStateCount(cell.getState()) > 1) {
                // digit occurs more than once
                return false;
            }
        }
        final int total = this.getTotal();
        final int emptyCount = this.getStateCount(SCell.EMPTY);
        final int expectedSum = this.specification.getSum();
        if (total + emptyCount > expectedSum) {
            // sum of digits filled in is too high
            // N.B. empty cells will contain at least a 1
            return false;
        }
        return emptyCount != 0 || total == expectedSum;
    }

    @Override
    public String toString() {
        return specification.toString();
    }

    /**
     * Returns a verbose string representation.
     *
     * @return verbose string representation of {@code this}
     */
    public String toStringLong() {
        return "{ specification" + specification + " }";
    }

    /**
     * Reads a list of entries from a given scanner.
     *
     * @param scanner the given scanner
     * @return the scanned list of entries
     * @post white space has been skipped on scanner
     */
    static List<SEntry> scanEntries(final Scanner scanner) {
        List<SEntry> result = new ArrayList<>();
        Pattern original = scanner.delimiter();
        scanner.skip("\\p{javaWhitespace}*");
        scanner.useDelimiter("");
        while (scanner.hasNext("[1-9]")) {
            scanner.useDelimiter(original);
            try {
                result.add(new SEntry(scanner));
            } catch (Exception e) {
                throw new IllegalArgumentException(SEntry.class.getSimpleName()
                        + ".scanEntries(Scanner).pre failed: after " + result.size() + " entries");
            }
            scanner.skip("\\p{javaWhitespace}*");
            scanner.useDelimiter("");
        }
        scanner.useDelimiter(original);
        return result;
    }
}
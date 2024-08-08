package ypa.command;

import java.util.Stack;

/**
 * Facilities for an undo-redo mechanism, on the basis of commands.
 */
public class UndoRedo {

    /** Stack of executed commands for undoing. */
    private final Stack<Command> undoStack;

    /** Stack of undone commands for redoing. */
    private final Stack<Command> redoStack;

    /**
     * Constructs an UndoRedo facility.
     */
    public UndoRedo() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    /**
     * Returns whether an {@code undo} is possible.
     *
     * @return whether {@code undo} is possible
     */
    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    /**
     * Returns whether a {@code redo} is possible.
     *
     * @return {@code redo().pre}
     */
    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    /**
     * Returns command most recently done.
     *
     * @return command at top of undo stack
     * @throws IllegalStateException if precondition failed
     * @pre {@code canUndo()}
     */
    public Command lastDone() throws IllegalStateException {
        if (!canUndo()) {
            throw new IllegalStateException("Cannot undo");
        }
        return undoStack.peek();
    }

    /**
     * Returns command most recently undone.
     *
     * @return command at top of redo stack
     * @throws IllegalStateException if precondition failed
     * @pre {@code canRedo()}
     */
    public Command lastUndone() throws IllegalStateException {
        if (!canRedo()) {
            throw new IllegalStateException("Cannot redo");
        }
        return redoStack.peek();
    }

    /**
     * Clears all undo-redo history.
     *
     * @modifies {@code this}
     */
    public void clear() {
        undoStack.clear();
        redoStack.clear();
    }

    /**
     * Adds given command to the do-history.
     * If the command was not yet executed, then it is first executed.
     *
     * @param command the command to incorporate
     * @modifies {@code this}
     */
    public void did(final Command command) {
        if (!command.isExecuted()) {
            command.execute();
        }
        undoStack.push(command);
        redoStack.clear();
    }

    /**
     * Undo the most recently done command, optionally allowing it to be redone.
     *
     * @param redoable whether to allow redo
     * @throws IllegalStateException if precondition violated
     * @pre {@code canUndo()}
     * @modifies {@code this}
     */
    public void undo(final boolean redoable) throws IllegalStateException {
        if (!canUndo()) {
            throw new IllegalStateException("Cannot undo");
        }
        Command command = undoStack.pop();
        command.revert();
        if (redoable) {
            redoStack.push(command);
        }
    }

    /**
     * Redo the most recently undone command.
     *
     * @throws IllegalStateException if precondition violated
     * @pre {@code canRedo()}
     * @modifies {@code this}
     */
    public void redo() throws IllegalStateException {
        if (!canRedo()) {
            throw new IllegalStateException("Cannot redo");
        }
        Command command = redoStack.pop();
        command.execute();
        undoStack.push(command);
    }

    /**
     * Undo all done commands.
     *
     * @param redoable whether to allow redo
     * @modifies {@code this}
     */
    public void undoAll(final boolean redoable) {
        while (canUndo()) {
            undo(redoable);
        }
    }

    /**
     * Redo all undone commands.
     *
     * @modifies {@code this}
     */
    public void redoAll() {
        while (canRedo()) {
            redo();
        }
    }

}

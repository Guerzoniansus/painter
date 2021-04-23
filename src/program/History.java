package program;

import commands.Command;

import java.util.ArrayList;
import java.util.List;

public class History {

    private List<Command> historyCommands;
    private List<Command> undoneCommands;

    public History() {
        historyCommands = new ArrayList<>();
        undoneCommands = new ArrayList<>();
    }

    /**
     * Undo the last action. Does nothing if there is nothing to undo.
     */
    public void undo() {
        
    }

    /**
     * Redo the last undone action(s). Does nothing if there is nothing to redo.
     */
    public void redo() {

    }

    /**
     * Add a command to the list of commands
     * @param command
     */
    public void addCommand(Command command) {
        historyCommands.add(command);
    }

}

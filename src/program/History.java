package program;

import commands.Command;

import java.util.ArrayList;
import java.util.List;

public class History {

    private List<Command> historyCommands;

    /**
     * Commands that undo() has been used on (and that redo() can be used on)
     */
    private List<Command> undoneCommands;

    public History() {
        historyCommands = new ArrayList<>();
        undoneCommands = new ArrayList<>();
    }

    /**
     * Undo the last action. Does nothing if there is nothing to undo.
     */
    public void undo() {
        if (historyCommands.size() > 0){
            // Undo last command
            Command command = historyCommands.get(historyCommands.size() -1);
            command.undo();

            // Update the history lists
            historyCommands.remove(command);
            undoneCommands.add(command);

            System.out.println("Undo " + command.getClass().getSimpleName());
        }
    }

    /**
     * Redo the last undone action(s). Does nothing if there is nothing to redo.
     */
    public void redo() {
        if (undoneCommands.size() > 0){
            // Execute the last undone command
            Command command = undoneCommands.get(undoneCommands.size() -1);
            command.execute();

            // Update the history lists
            historyCommands.add(command);
            undoneCommands.remove(command);

            System.out.println("Redo " + command.getClass().getSimpleName());
        }
    }

    /**
     * Add a command to the list of commands
     * @param command
     */
    public void addCommand(Command command) {
        historyCommands.add(command);
    }
}

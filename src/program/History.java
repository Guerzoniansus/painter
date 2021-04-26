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
        if (historyCommands.size() > 0){
            Command command = historyCommands.get(historyCommands.size() -1);
            command.undo();
            historyCommands.remove(command);
            undoneCommands.add(command);
        }
        else 
            return;
    }

    /**
     * Redo the last undone action(s). Does nothing if there is nothing to redo.
     */
    public void redo() {
        if (undoneCommands.size() > 0){
            Command command = undoneCommands.get(undoneCommands.size() -1);
            command.execute();
            historyCommands.add(command);
            undoneCommands.remove(command);
        }
        else 
            return;
    }

    /**
     * Add a command to the list of commands
     * @param command
     */
    public void addCommand(Command command) {
        historyCommands.add(command);
    }
}

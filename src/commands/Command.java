package commands;

/**
 * Represents a command that can be executed by the program
 */
public interface Command {

    /**
     * Execute this command
     */
    void execute();

    /**
     * Undo this command
     */
    void undo();
}

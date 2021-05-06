package program;

import commands.*;
import gui.GUI;
import shapes.Figure;
import tools.Tool;
import tools.Tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class PainterProgram extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    private static final String TITLE = "Painter";
    private static final int WINDOW_WIDTH = 1300;
    private static final int WINDOW_HEIGHT = 975;
    public static final String SAVE_FILE_PATH = "resources/shape.txt";

    private final int UNDO_BUTTON = 90; // Z key
    private final int REDO_BUTTON = 89; // Y key
    private final int SAVE_BUTTON = 83; // S key
    private final int LOAD_BUTTON = 76; // L key

    private Window window;

    private List<Figure> figures;

    private GUI gui;

    private Tool currentTool;

    private History history;

    /**
     * If false, don't check for keyboard inputs
     */
    private boolean acceptKeyboardInput;
    

    public PainterProgram() {
        window = new Window(TITLE, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        figures = new ArrayList();
        history = new History();
        acceptKeyboardInput = true;

        // Initialisation stuff
        this.setLayout(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        // Make tools ready to use
        Tools.createTools(this);
        setActiveTool(Tools.TOOL_SELECT);

        // Create GUI
        gui = new GUI(this);
        gui.getButtons().forEach(button -> this.add(button));
        gui.updateActiveTool(currentTool);

        // Update screen
        repaint();
    }

    /**
     * Set the newly selected tool
     * @param newTool The new tool to select as active
     */
    public void setActiveTool(Tool newTool) {
        if (currentTool != null) {
            currentTool.deActivate();
        }

        currentTool = newTool;
        currentTool.activate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Don't remove this line
        g.setFont(new Font("Arial", Font.BOLD, 24));

        currentTool.onDraw(g);
        drawLastCommand(g);
        drawFigures(g);

    }

    /**
     * Draw all figures on the screen
     * @param g The graphics object to draw to
     */
    public void drawFigures(Graphics g) {
        for (Figure figure : figures) {
            figure.draw(g);
        }
    }

    /**
     * Function that draws the last used command
     * @param g The graphics object to draw to
     */
    private void drawLastCommand(Graphics g) {
        String lastCommand = history.getLastCommand() != null ?
                history.getLastCommand().getName() : "";

        g.setColor(Color.BLACK);
        g.drawString("Last action: " + lastCommand, 300, 30);
    }

    /**
     * Add a figure to the list of figures
     * @param figure The figure to add
     */
    public void addFigure(Figure figure) {
        figures.add(figure);
    }

    /**
     * Remove a figure from the list of figures
     * @param figure
     */
    public void removeFigure(Figure figure) {
        figures.remove(figure);
    }

    /**
     * Return the currently selected tool
     * @return The currently selected tool
     */
    public Tool getActiveTool() {
        return currentTool;
    }

    /**
     * Get a list of all figures
     * @return The list of figures
     */
    public List<Figure> getFigures() {
        return figures;
    }

    /**
     * Disable the painter program from listening to keyboard inputs.
     * Tools can still listen to inputs on their own.
     */
    public void disableKeyboardInput() {
        acceptKeyboardInput = false;
    }

    /**
     * Enable the painter program for listening to keyboard inputs
     */
    public void enableKeyboardInput() {
        acceptKeyboardInput = true;
    }


    /**
     * Undo the last command
     */
    public void undo() {
        history.undo();
        repaint();
    }

    /**
     * Redo the last undone action
     */
    public void redo() {
        history.redo();
        repaint();
    }

    /**
     * Save the current screen to file
     */
    public void save() {
        // Only save if there is actually something to save
        if (figures.size() > 0) {
            SaveCommand saveCommand = new SaveCommand(figures);
            executeCommand(saveCommand);
        }
    }

    /**
     * Replace the screen by loading a figure from file
     */
    public void load() {
        currentTool.deActivate();

        LoadCommand loadCommand = new LoadCommand(this);
        executeCommand(loadCommand);

        currentTool.activate();
    }

    /**
     * Execute a command and save it in the history
     * @param command The command to execute
     */
    public void executeCommand(Command command) {
        command.execute();
        history.addCommand(command);
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (acceptKeyboardInput) {
            switch (e.getKeyCode()) {
                case UNDO_BUTTON:
                    undo();
                    break;
                case REDO_BUTTON:
                    redo();
                    break;
                case SAVE_BUTTON:
                    save();
                    break;
                case LOAD_BUTTON:
                    load();
                    break;
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

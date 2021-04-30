package gui;

import program.PainterProgram;
import tools.Tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GUI {

    private PainterProgram painter;

    private final int STARTING_X = 0;
    private final int STARTING_Y = 0;
    private final int BUTTON_SIZE = 100;
    private final int NUMBER_OF_COLS = 1;
    private final String IMAGE_PATH  = "resources/";

    private Map<Tool, ToolButton> buttons;

    public GUI(PainterProgram painter) {
        this.painter = painter;

        buttons = new HashMap<>();
        createButtons();

        updateActiveTool(painter.getActiveTool());
    }

    /**
     * Create all the GUI buttons
     */
    private void createButtons() {
        ButtonGenerator grid = new ButtonGenerator(STARTING_X, STARTING_Y, BUTTON_SIZE, NUMBER_OF_COLS);

        ToolButton selectButton = grid.generateButton(Tool.TOOL_SELECT, IMAGE_PATH + "pointer.png", this);
        buttons.put(selectButton.getTool(), selectButton);

        ToolButton rectangleButton = grid.generateButton(Tool.TOOL_RECTANGLE, IMAGE_PATH + "square.png", this);
        buttons.put(rectangleButton.getTool(), rectangleButton);

        ToolButton ellipseButton = grid.generateButton(Tool.TOOL_ELLIPSE, IMAGE_PATH + "circle.png", this);
        buttons.put(ellipseButton.getTool(), ellipseButton);
    }

    /**
     * Updates the new active tool. Communicates this with the Painter Program and also changes button borders
     * to properly show what is the new active tool.
     * @param newTool The new tool to become active
     */
    public void updateActiveTool(Tool newTool) {
        ToolButton oldToolButton = buttons.get(painter.getActiveTool());
        oldToolButton.setActive(false);

        painter.setActiveTool(newTool);
        showActiveTool(buttons.get(newTool));
    }

    /**
     * Get all the tool buttons
     * @return A list of all buttons
     */
    public ArrayList<ToolButton> getButtons() {
        return new ArrayList<>(buttons.values());
    }

    /**
     * Set this button to active and show a border around it
     * @param button The button to show as active
     */
    public void showActiveTool(ToolButton button) {
        button.showBorder();
        button.setActive(true);
    }

    /**
     * A helper class uses to generate buttons in a grid pattern
     */
    private class ButtonGenerator {
        private int startingX, startingY;
        private int x, y, buttonSize, cols;
        private int currentCol;

        /**
         * Create a generator for buttons that generates all the relevant coordinates in a grid pattern
         * @param startingX The top left X position of the grid
         * @param startingY The top left Y value of the grid
         * @param buttonSize The size of each button
         * @param cols The amount of columns that each row should have. After this amount is reached, a new row will be made
         */
        public ButtonGenerator(int startingX, int startingY, int buttonSize, int cols) {
            this.startingX = startingX;
            this.startingY = startingY;
            this.x = startingX;
            this.y = startingY;
            this.buttonSize = buttonSize;
            this.cols = cols;

            currentCol = 0;

            // Dont be a weird person that wants negative amounts of columns
            if (this.cols < 1) {
                this.cols = 1;
            }
        }

        /**
         * Generate a new button
         * @param tool The tool that belongs to this button
         * @param imagePath The path of the image
         * @param gui The GUI object this button belongs to
         * @return The newly created ToolButton object
         */
        public ToolButton generateButton(Tool tool, String imagePath, GUI gui) {
            ToolButton button = new ToolButton(x, y, buttonSize, tool, imagePath, gui);
            updateGrid();

            return button;
        }

        /**
         * Update the (non-existent) grid's positions
         */
        private void updateGrid() {
            currentCol++;

            // Generate new column
            if (currentCol < cols) {
                x += buttonSize;
            }

            // Generate new row
            else if (currentCol >= cols) {
                currentCol = 0;
                x = startingX;
                y += buttonSize;
            }
        }
    }

}

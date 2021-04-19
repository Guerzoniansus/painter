package gui;

import tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A button used to select a tool
 */
public class ToolButton extends JLabel {

    private Tool tool;
    private boolean active;
    private GUI gui;

    public ToolButton(int x, int y, int size, Tool tool, String imagePath, GUI gui) {
        super(new ImageIcon(imagePath));
        this.setLocation(x, y);
        this.setSize(size, size);

        this.tool = tool;
        this.active = false;
        this.gui = gui;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                active = true;
                gui.updateActiveTool(tool);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                showBorder();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                removeBorder();
            }
        });
    }

    /**
     * Get the tool that belongs to this button
     * @return The tool that belongs to this button
     */
    public Tool getTool() {
        return tool;
    }

    /**
     * Show a border around this button
     */
    public void showBorder() {
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    /**
     * Remove the border around this button
     */
    public void removeBorder() {
        if (active == false) {
            this.setBorder(null);
        }
    }

    /**
     * Set this button to "active", meaning the tool is currently selected
     * @param active Whether this button should be active or not
     */
    public void setActive(boolean active) {
        this.active = active;

        if (active) {
            showBorder();
        }

        else {
            removeBorder();
        }
    }

}

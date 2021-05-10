package tools;

import commands.CreateOrnamentCommand;
import program.PainterProgram;
import shapes.Figure;
import shapes.Ornament;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class ToolOrnament extends Tool implements KeyListener {

    // Keyboard code
    private static final int ENTER_BUTTON = 10;
    private static final int BACKSPACE_BUTTON = 8;

    private Figure selectedFigure;

    private double clickX;
    private double clickY;

    private String typedCharacters;

    /**
     * If typing, typing = true, cannot click again before finishing message
     */
    private boolean typing = false;

    /**
     * Creates a tool that can add text to figures
     * @param painter
     */
    public ToolOrnament(PainterProgram painter) {
        super(painter);
        selectedFigure = null;
        typing = false;
        clickX = 0;
        clickY = 0;
        typedCharacters = "";
    }

    @Override
    public void onDraw(Graphics g) {
        if (typing) {
            if (typedCharacters.equals(""))
                // Show keyboard input is enabled
                g.drawString("_", (int) clickX, (int) clickY);

            else
                g.drawString(typedCharacters, (int) clickX, (int) clickY);
        }

        if (selectedFigure != null) {
            selectedFigure.drawSelectionBorder(g);
        }
    }

    @Override
    public void activate() {
        painter.addMouseListener(this);
        painter.addKeyListener(this);

        // Get selected figure, only do things if ONE figure is selected
        if (((ToolSelect) Tools.TOOL_SELECT).getSelectedFigures().size() > 0) {
            selectedFigure = ((ToolSelect) Tools.TOOL_SELECT).getSelectedFigures().get(0);
            ((ToolSelect) Tools.TOOL_SELECT).clearSelectedFigures();
        }

        else {
            selectedFigure = null;
        }

        typing = false;
        clickX = 0;
        clickY = 0;
    }

    @Override
    public void deActivate() {
        painter.removeMouseListener(this);
        painter.removeKeyListener(this);

        selectedFigure = null;
        typedCharacters = "";
    }

    private void createOrnament() {
        String position = determinePosition(selectedFigure, clickX, clickY);

        CreateOrnamentCommand command = new CreateOrnamentCommand(typedCharacters, position, selectedFigure, painter);
        painter.executeCommand(command);

        selectedFigure = command.getOrnament();
        painter.repaint();
    }

    private String determinePosition(Figure figure, double clickX, double clickY) {
        // Center point
        int figureX = figure.getX();
        int figureY = figure.getY();

        // Intentionally prioritizing y above x
        if (clickY < figureY)
            return Ornament.TOP;

        else if (clickY > (figureY + figure.getHeight()))
            return Ornament.BOTTOM;

        else if (clickX < figureX)
            return Ornament.LEFT;

        else return Ornament.RIGHT;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Only do stuff if there are actually any selected figures
        if (selectedFigure == null)
            return;

        // Don't allow creating a new ornament when still typing the previous one
        if (typing == true)
            return;

        // Mouse click point
        clickX = e.getPoint().getX();
        clickY = e.getPoint().getY();

        // Only allow clicks outside the figure to make determining position easier
        if (selectedFigure.contains(clickX, clickY))
            return;

        else {
            typing = true;
            painter.disableKeyboardInput();
            painter.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Only let user type if typing somewhere
        if (typing == false)
            return;

        // Make backspace work by removing the last letter
        if ((int) e.getKeyChar() == BACKSPACE_BUTTON) {
            if (typedCharacters.length() > 0) {
                typedCharacters = typedCharacters.substring(0, typedCharacters.length() - 1);
                painter.repaint();
            }

            return;
        }

        // Pressed enter, create ornament
        else if ((int) e.getKeyChar() == ENTER_BUTTON) {
            typing = false;
            painter.enableKeyboardInput();
            createOrnament();
            typedCharacters = "";

            return;
        }

        else {
            // Add typed letter to the typed text
            typedCharacters = typedCharacters + e.getKeyChar();
            painter.repaint();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {

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
    public void keyReleased(KeyEvent e) {

    }
}

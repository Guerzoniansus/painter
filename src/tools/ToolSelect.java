package tools;

import commands.Command;
import commands.CreateGroupCommand;
import program.PainterProgram;
import shapes.Figure;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A tool used for selecting figures, moving them, resizing them and grouping them
 */
public class ToolSelect extends Tool implements KeyListener, MouseWheelListener {

    // These numbers have been found out by printing e.getButton() in the mouse clicked event
    private final int LEFT_MOUSE_BUTTON = 1;
    private final int GROUP_BUTTON = 71; // the G key

    private final double RESIZE_FACTOR = 30;

    private List<Figure> selectedFigures;

    private Point previousMousePoint;

    protected ToolSelect(PainterProgram painter) {
        super(painter);
        selectedFigures = new ArrayList<>();
        previousMousePoint = null;
    }

    @Override
    public void onDraw(Graphics g) {
        selectedFigures.forEach(figure -> figure.drawSelectionBorder(g));
    }

    @Override
    public void activate() {
        painter.addMouseListener(this);
        painter.addMouseMotionListener(this);
        painter.addMouseWheelListener(this);
        painter.addKeyListener(this);
    }

    @Override
    public void deActivate() {
        selectedFigures.clear();
        painter.removeMouseListener(this);
        painter.removeMouseWheelListener(this);
        painter.removeMouseMotionListener(this);
        painter.removeKeyListener(this);
    }


    /**
     * Resize all figures
     * @param increaseIfTrueDecreaseIfFalse Speaks for itself
     */
    private void resizeFigures(boolean increaseIfTrueDecreaseIfFalse) {
        // Use negative factor when decreasing size
        final double factor = increaseIfTrueDecreaseIfFalse == true ? RESIZE_FACTOR : 0 - RESIZE_FACTOR;

        selectedFigures.forEach(figure -> figure.resize(factor));
        painter.repaint();
    }

    /**
     * Select all shapes that are clicked on by the mouse
     * @param point The point of the mouse click
     */
    private void selectShapes(Point point) {
        boolean selectedAtLeastOneFigure = false;

        for (Figure figure : painter.getFigures()) {
            if (figure.contains((int) point.getX(), (int) point.getY())) {
                selectedFigures.add(figure);
                selectedAtLeastOneFigure = true;
            }
        }

        // Didn't click on a shape, clear selections
        if (selectedAtLeastOneFigure == false) {
            selectedFigures.clear();
        }

        painter.repaint();
    }

    /**
     * Checks if a coordinate is on top of a figure
     * @param x The x coordinate
     * @param y The y coordinate
     * @return True if on top of a figure, false if not
     */
    private boolean isPointOnAFigure(int x, int y) {
        for (Figure figure : painter.getFigures()) {
            if (figure.contains(x, y)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Move all figures
     */
    private void moveFigures(int horizontalDistance, int verticalDistance) {
        selectedFigures.forEach(figure -> figure.move(horizontalDistance, verticalDistance));
    }

    /**
     * Checks if any figures are selected
     * @return True if 1 or more figures are selected, false otherwise
     */
    private boolean areFiguresSelected() {
        return selectedFigures.size() > 0;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        /* ===== Resizing ===== */

        if (areFiguresSelected()) {
            // If wheel rotation < 0, it means user scrolled UP, which means INCREASE size
            // If wheel rotation >= 0, it means user scrolled DOWN, which means DECREASE size (NEGATIVE factor)
            boolean increaseIfTrueDecreaseIfFalse = e.getWheelRotation() < 0 ? true : false;
            resizeFigures(increaseIfTrueDecreaseIfFalse);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == LEFT_MOUSE_BUTTON) {
            // Select a shape
            selectShapes(e.getPoint());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == GROUP_BUTTON) {
            // Execute the command
            CreateGroupCommand createGroupCommand = new CreateGroupCommand(painter, new ArrayList<>(selectedFigures));
            painter.executeCommand(createGroupCommand);

            // Reset the selection to now be the new group
            selectedFigures.clear();
            selectedFigures.add(createGroupCommand.getGroup());

            painter.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        /* ===== Creating a shape by dragging mouse ===== */
        if (previousMousePoint != null) {
            int horizontalDistance = (int) (e.getPoint().getX() - previousMousePoint.getX());
            int verticalDistance = (int) (e.getPoint().getY() - previousMousePoint.getY());
            moveFigures(horizontalDistance, verticalDistance);
            previousMousePoint = e.getPoint();
            painter.repaint();
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        /* ===== Set starting point for moving a shape ===== */
        if (isPointOnAFigure((int) e.getPoint().getX(), (int) e.getPoint().getY())) {
            previousMousePoint = e.getPoint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        /* ===== Remove starting point for moving a shape ===== */
        previousMousePoint = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

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

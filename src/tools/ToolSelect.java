package tools;

import commands.CreateGroupCommand;
import commands.MoveCommand;
import commands.ResizeCommand;
import program.PainterProgram;
import shapes.Figure;
import visitors.MoveFigureVisitor;
import visitors.Visitor;

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

    private final int RESIZE_AMOUNT = 30;

    private List<Figure> selectedFigures;

    // Used for moving shapes around
    private Point originalMousePoint;
    private Point previousMousePoint;

    protected ToolSelect(PainterProgram painter) {
        super(painter);
        selectedFigures = new ArrayList<>();
        previousMousePoint = null;
        originalMousePoint = null;
    }

    @Override
    public void onDraw(Graphics g) {
        selectedFigures.forEach(figure -> figure.drawSelectionBorder(g));
    }

    /**
     * Get a list of figures selected by this tool. This tool keeps selection in memory even when not active.
     * @return A list of selected figures
     */
    public List<Figure> getSelectedFigures() {
        return selectedFigures;
    }

    /**
     * Clear the selected figures
     */
    public void clearSelectedFigures() {
        selectedFigures.clear();
    }

    @Override
    public void activate() {
        painter.addMouseListener(this);
        painter.addMouseMotionListener(this);
        painter.addMouseWheelListener(this);
        painter.addKeyListener(this);
        selectedFigures.clear();
    }

    @Override
    public void deActivate() {
        previousMousePoint = null;
        originalMousePoint = null;
        painter.removeMouseListener(this);
        painter.removeMouseWheelListener(this);
        painter.removeMouseMotionListener(this);
        painter.removeKeyListener(this);
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
     * Checks if any figures are selected
     * @return True if 1 or more figures are selected, false otherwise
     */
    private boolean areFiguresSelected() {
        return selectedFigures.size() > 0;
    }

    /**
     * Checks if a coordinate is on top of a figure
     * @param x The x coordinate
     * @param y The y coordinate
     * @return True if on top of a figure, false if not
     */
    private boolean isPointOnAFigure(double x, double y) {
        for (Figure figure : painter.getFigures()) {
            if (figure.contains(x, y)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        /* ===== Resizing ===== */

        if (areFiguresSelected()) {
            // If wheel rotation < 0, it means user scrolled UP, which means INCREASE size
            // If wheel rotation >= 0, it means user scrolled DOWN, which means DECREASE size (NEGATIVE factor)
            boolean growIfTrueShrinkIfFalse = e.getWheelRotation() < 0 ? true : false;

            // Use negative factor when decreasing size
            final int amount = growIfTrueShrinkIfFalse == true ? RESIZE_AMOUNT : RESIZE_AMOUNT * -1;
            ResizeCommand resizeCommand = new ResizeCommand(painter, amount, selectedFigures);
            painter.executeCommand(resizeCommand);
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
        // Move shape
        if (previousMousePoint != null) {
            int horizontalDistance = (int) (e.getPoint().getX() - previousMousePoint.getX());
            int verticalDistance = (int) (e.getPoint().getY() - previousMousePoint.getY());

            // Move them so user can see feedback
            Visitor visitor = new MoveFigureVisitor(horizontalDistance, verticalDistance);
            selectedFigures.forEach(figure -> figure.accept(visitor));

            previousMousePoint = e.getPoint();
            painter.repaint();

        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        /* ===== Set starting point for moving a shape ===== */
        if (isPointOnAFigure( e.getPoint().getX(), e.getPoint().getY())) {
            previousMousePoint = e.getPoint();
            originalMousePoint = previousMousePoint;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (previousMousePoint != null) {
            // Calculate total distance moved
            int totalDistanceHorizontal = (int) (e.getPoint().getX() - originalMousePoint.getX());
            int totalDistanceVertical = (int) (e.getPoint().getY() - originalMousePoint.getY());

            // First reset figures back to their original position
            Visitor visitor = new MoveFigureVisitor(totalDistanceHorizontal * -1, totalDistanceVertical * - 1);
            selectedFigures.forEach(figure -> figure.accept(visitor));

            // then do the entire movement that can be undo'd
            MoveCommand moveCommand = new MoveCommand(painter, selectedFigures, totalDistanceHorizontal, totalDistanceVertical);
            painter.executeCommand(moveCommand);

            //  Reset starting point for moving a shape
            previousMousePoint = null;
            originalMousePoint = null;
        }
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

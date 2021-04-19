package tools;

import program.PainterProgram;
import shapes.Figure;
import shapes.Rectangle;
import shapes.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * A tool that creates shapes, such as rectangles or ellipses
 */
public abstract class ToolShapeCreator extends Tool {

    private boolean dragging;
    private Rectangle selection;

    private Point clickPoint;

    protected ToolShapeCreator(PainterProgram painter) {
        super(painter);
        dragging = false;
        selection = null;
        clickPoint = null;
    }

    /**
     * The figure that this object is supposed to create.
     * Used when adding the figure on the screen as well as displaying the selection.
     */
    protected abstract Shape createFigure();

    /**
     * Determine the selection made with the mouse
     * @param point1 The first click point
     * @param point2 The second click point
     * @return Returns a rectangle from point 1 to point two
     */
    private Rectangle determineSelection(Point point1, Point point2) {
        int x = Math.min(point1.x, point2.x);
        int y = Math.min(point1.y, point2.y);

        int width = Math.max(point1.x, point2.x) - x;
        int height = Math.max(point1.y, point2.y) - y;

        return new Rectangle(x, y, width, height);
    }

    /**
     * Function used to draw the current selection (mouse drag movement) on the screen
     * @param g The graphics object to draw to
     */
    private void showSelection(Graphics g) {
        if (selection != null) {
            createFigure().draw(g);
        }
    }

    /**
     * Retrusn a copy of the rectangle representing the selection made by the user's mouse
     * @return A selection rectangle
     */
    public Rectangle getSelection() {
        return new Rectangle(selection.getX(), selection.getY(), selection.getWidth(), selection.getHeight());
    }

    @Override
    public void onDraw(Graphics g) {
        showSelection(g);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clickPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragging == true) {
            painter.addFigure((Figure) createFigure());

            selection = null;
            dragging = false;
            painter.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dragging = true;
        selection = determineSelection(clickPoint, e.getPoint());
        painter.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
    public void mouseWheelMoved(MouseWheelEvent e) {

    }
}

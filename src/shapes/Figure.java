package shapes;

import visitors.Visitor;

import java.awt.*;

public interface Figure {

     Color FILL_COLOR = Color.BLACK;
     Color BORDER_COLOR = Color.RED;

    /**
     * Draw this figure on the screen
     * @param g The graphics object to draw to
     */
    void draw(Graphics g);

    /**
     * Mark this figure as selected
     */
    void drawSelectionBorder(Graphics g);

    /**
     * Returns whether this figure contains a specific point
     * @param x The x coordinate of the point
     * @param y The y coordinate of the point
     * @return True if the point is inside this figure, false if not
     */
    boolean contains(double x, double y);

    /**
     * Accept a visitor
     * @param visitor The visitor to accept
     */
    void accept(Visitor visitor);

    String getName();

    int getX();

    int getY();

    int getWidth();

    int getHeight();


}

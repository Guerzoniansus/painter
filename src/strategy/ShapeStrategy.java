package strategy;

import java.awt.*;
import shapes.Shape;

public interface ShapeStrategy {

    Color FILL_COLOR = Color.BLACK;
    
    /**
     * Draw this figure on the screen
     * @param g The graphics object to draw to
     */

    void draw (Graphics g, Shape shape);

    String toString(Shape shape);
}
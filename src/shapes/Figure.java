package shapes;

import java.awt.*;

public interface Figure {

    /**
     * Draw this figure on the screen
     * @param g The graphics object to draw to
     */
    void draw(Graphics g);

    /**
     * Resize this figure
     * @param factor The factor with which to multiple the original size
     */
    void resize(double factor);

    /**
     * Move this figure a certain distance. Use negative numbers to move to the top left.
     * @param horizontalDistance  The amount of distance to travel horizontally.
     * @param verticalDistance The amount of distance to travel vertically
     */
    void move(int horizontalDistance, int verticalDistance);

}

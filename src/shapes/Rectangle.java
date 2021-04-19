package shapes;

import java.awt.*;

/**
 * A simple rectangle
 */
public class Rectangle extends Shape {

    public Rectangle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }


}

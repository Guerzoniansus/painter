package shapes;

import java.awt.*;

/**
 * A simple ellipse
 */
public class Ellipse extends Shape {

    public Ellipse(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.fillOval(x, y, width, height);
    }

}

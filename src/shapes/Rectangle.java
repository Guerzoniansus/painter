package shapes;

import java.awt.*;

public class Rectangle extends Shape implements Figure {

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }
}

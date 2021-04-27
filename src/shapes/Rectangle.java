package shapes;

import java.awt.*;

/**
 * A simple rectangle
 */
public class Rectangle extends Shape {

    public Rectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(FILL_COLOR);
        g.fillRect((int) x, (int) y, (int) width, (int) height);
    }

    public void drawSelectionBorder(Graphics g) {
        g.setColor(BORDER_COLOR);

        Graphics2D g2d = (Graphics2D) g.create();

        // Set the stroke of the copy, not the original
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                0, new float[]{9}, 0);

        g2d.setStroke(dashed);

        // Draw to the copy
        g2d.drawRect((int) x, (int) y, (int) width, (int) height);

        // Get rid of the copy
        g2d.dispose();
    }

    @Override
    public boolean contains(double x, double y) {
        return new java.awt.Rectangle((int) this.x, (int) this.y, (int) width, (int) height).contains(x, y);
    }

    @Override
    public String getName(){
        return "rectangle";
    }
}

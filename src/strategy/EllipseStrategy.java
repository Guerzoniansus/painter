package strategy;

import shapes.Shape;
import java.awt.*;

public class EllipseStrategy implements ShapeStrategy{

    private static EllipseStrategy instance = new EllipseStrategy();

    private EllipseStrategy(){}

    public static EllipseStrategy getInstance()
    {
        return instance;
    }
    
    @Override
    public void draw(Graphics g, Shape shape) {
        g.setColor(FILL_COLOR);
        g.fillOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
    }

    @Override
    public String toString(Shape shape) {
        return "ellipse";
    } 
}

package strategy;

import shapes.Shape;
import java.awt.*;

public class RectangleStrategy implements ShapeStrategy{

    private static RectangleStrategy instance = new RectangleStrategy();

    private RectangleStrategy(){}

    public static RectangleStrategy getInstance()
    {
        return instance;
    }
    
    @Override
    public void draw(Graphics g, Shape shape) {
        g.setColor(FILL_COLOR);
        g.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
    }

    @Override
    public String toString(Shape shape) {
        return "rectangle";
    } 
}

package strategy;

import shapes.Shape;
import java.awt.*;

public class EllipseStrategy implements ShapeStrategy{

    private static volatile EllipseStrategy instance = null;

    public EllipseStrategy(){}

    public EllipseStrategy getInstance()
    {
        if (instance == null)
        {
            // To make thread safe
            synchronized (EllipseStrategy.class)
            {
                // check again as multiple threads
                // can reach above step
                if (instance==null)
                instance = new EllipseStrategy();
            }
        }
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

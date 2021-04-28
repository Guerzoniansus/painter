package strategy;

import shapes.Shape;
import java.awt.*;

public class RectangleStrategy implements ShapeStrategy{

    private static volatile RectangleStrategy instance = null;

    public RectangleStrategy() {}

    public static RectangleStrategy getInstance()
    {
        if (instance == null)
        {
            // To make thread safe
            synchronized (RectangleStrategy.class)
            {
                // check again as multiple threads
                // can reach above step
                if (instance==null)
                instance = new RectangleStrategy();
            }
        }
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

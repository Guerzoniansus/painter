package shapes;

import strategy.ShapeStrategy;
import visitors.Visitor;

/**
 * A parent class with some implementation details for common shapes
 */
public abstract class Shape implements Figure {

    protected int x, y, width, height;

    ShapeStrategy shapeStrategy;

    Shape(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * Get X
     * @return X
     */
    public int getX() {
        return x;
    }

    /**
     * Get Y
     * @return Y
     */
    public int getY() {
        return y;
    }

    /**
     * Set the X
     * @param newX The new X
     */
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * Set the Y
     * @param newY The new Y
     */
    public void setY(int newY) {
        this.y = newY;
    }

    /**
     * Get this shape's width
     * @return The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get this shape's height
     * @return The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the new width
     * @param width The new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Set the new height
     * @param height The new height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    public void setStrategy(ShapeStrategy strategy){
        shapeStrategy = strategy;
    }

}

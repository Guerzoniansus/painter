package shapes;

import visitors.Visitor;

/**
 * A parent class with some implementation details for common shapes
 */
public abstract class Shape implements Figure {

    protected double x, y, width, height;

    protected final int MINIMUM_SIZE = 5;

    Shape(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void resize(double amount) {
        final double newWidth = (int) (width + amount);
        final double newHeight = (int) (height + amount);

        // Prevent shapes from becoming impossibly small
        if (amount < 0) {
            if (newWidth < MINIMUM_SIZE || newHeight < MINIMUM_SIZE) {
                return;
            }
        }

        width = newWidth;
        height = newHeight;
    }

    @Override
    public void move(int horizontalDistance, int verticalDistance) {
        x += horizontalDistance;
        y += verticalDistance;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


    /**
     * Get X
     * @return X
     */
    public double getX() {
        return x;
    }

    /**
     * Get Y
     * @return Y
     */
    public double getY() {
        return y;
    }

    /**
     * Set the X
     * @param newX The new X
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * Set the Y
     * @param newY The new Y
     */
    public void setY(double newY) {
        this.y = newY;
    }

    /**
     * Get this shape's width
     * @return The width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Get this shape's height
     * @return The height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Set the new width
     * @param width The new width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Set the new height
     * @param height The new height
     */
    public void setHeight(double height) {
        this.height = height;
    }

}

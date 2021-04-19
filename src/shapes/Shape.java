package shapes;

/**
 * A parent class with some implementation details for common shapes
 */
public abstract class Shape implements Figure {

    protected int x, y, width, height;

    Shape(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void resize(double factor) {
        width = (int) (width * factor);
        height = (int) (height * factor);
    }

    @Override
    public void move(int horizontalDistance, int verticalDistance) {
        x += horizontalDistance;
        y += verticalDistance;
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
}

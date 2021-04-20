package shapes;

/**
 * A parent class with some implementation details for common shapes
 */
public abstract class Shape implements Figure {

    protected int x, y, width, height;

    protected final int MINIMUM_SIZE = 5;

    Shape(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void resize(double factor) {
        final int newWidth = (int) (width + factor);
        final int newHeight = (int) (height + factor);

        // Prevent shapes from becoming impossibly small
        if (factor < 0) {
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

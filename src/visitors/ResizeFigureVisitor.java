package visitors;

import shapes.Group;
import shapes.Shape;

public class ResizeFigureVisitor implements Visitor {

    protected final int MINIMUM_SIZE = 10;

    private final double POSITIVE_RESIZE_FACTOR = 1.1;
    private final double NEGATIVE_RESIZE_FACTOR = 0.9;
    private final double factor;

    /**
     * A visitor for resizing factors
     * @param factor The factor with which to resize the figure
     */
    public ResizeFigureVisitor(double factor) {
        this.factor = factor;
    }

    @Override
    public void visit(Shape shape) {
        // Calculate new width and height
        final double newWidth = shape.getWidth() * factor;
        final double newHeight = shape.getHeight() * factor;

        // Prevent shapes from becoming impossibly small
        if (factor < 1) {
            if (newWidth < MINIMUM_SIZE || newHeight < MINIMUM_SIZE) {
                return;
            }
        }

        // Set the new width and height
        shape.setWidth(newWidth);
        shape.setHeight(newHeight);
    }

    @Override
    public void visit(Group group) {
        group.getFigures().forEach(figure -> figure.accept(this));
    }
}

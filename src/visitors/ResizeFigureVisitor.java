package visitors;

import shapes.Group;
import shapes.Shape;

public class ResizeFigureVisitor implements Visitor {

    private final int MINIMUM_SIZE = 10;

    private final int amount;

    /**
     * A visitor for resizing figures
     * @param amount The amount with which to resize the figure
     */
    public ResizeFigureVisitor(int amount) {
        this.amount = amount;
    }

    @Override
    public void visit(Shape shape) {
        // Calculate new width and height
        final int newWidth = shape.getWidth() + amount;
        final int newHeight = shape.getHeight() + amount;

        // Prevent shapes from becoming impossibly small
        if (amount < 1) {
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

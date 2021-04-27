package visitors;

import shapes.Group;
import shapes.Shape;

public class MoveFigureVisitor implements Visitor {

    private final double horizontalDistance, verticalDistance;

    /**
     * A visitor for moving figures
     * @param horizontalDistance The horizontal distance to move
     * @param verticalDistance The vertical distance to move
     */
    public MoveFigureVisitor(double horizontalDistance, double verticalDistance) {
        this.horizontalDistance = horizontalDistance;
        this.verticalDistance = verticalDistance;
    }


    @Override
    public void visit(Shape shape) {
        shape.setX(shape.getX() + horizontalDistance);
        shape.setY(shape.getY() + verticalDistance);
    }

    @Override
    public void visit(Group group) {
        group.getFigures().forEach(figure -> figure.accept(this));
    }
}

package visitors;

import decorator.Ornament;
import shapes.Group;
import shapes.Shape;

public class MoveFigureVisitor implements Visitor {

    private final int horizontalDistance, verticalDistance;

    /**
     * A visitor for moving figures
     * @param horizontalDistance The horizontal distance to move
     * @param verticalDistance The vertical distance to move
     */
    public MoveFigureVisitor(int horizontalDistance, int verticalDistance) {
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


    @Override
    public void visit(Ornament ornament) {
        // TODO Auto-generated method stub
        
    }
}

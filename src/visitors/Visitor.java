package visitors;

import decorator.Ornament;
import shapes.Group;
import shapes.Shape;

public interface Visitor {

    /**
     * Visit this shape
     * @param shape The shape to visit
     */
    void visit(Shape shape);

    /**
     * Visit this group
     * @param group The group to visit
     */
    void visit(Group group);

	void visit(Ornament ornament);
}

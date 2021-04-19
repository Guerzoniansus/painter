package tools;

import program.PainterProgram;

import shapes.Ellipse;
import shapes.Rectangle;
import shapes.Shape;

/**
 * A tool used for drawing ellipses
 */
public class ToolEllipse extends ToolShapeCreator {

    protected ToolEllipse(PainterProgram painter) {
        super(painter);
    }

    @Override
    protected Shape createFigure() {
        Rectangle selection = getSelection();

        return new Ellipse(selection.getX(), selection.getY(), selection.getWidth(), selection.getHeight());
    }


}

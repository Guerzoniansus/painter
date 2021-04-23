package tools;

import program.PainterProgram;
import shapes.Shape;

/**
 * A tool used for drawing rectangles
 */
public class ToolRectangle extends ToolShapeCreator {

    protected ToolRectangle(PainterProgram painter) {
        super(painter);
    }


    @Override
    protected Shape createFigure() {
        return getSelection();
    }

}

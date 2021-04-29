package commands;

import shapes.Figure;
import program.PainterProgram;
import visitors.MoveFigureVisitor;
import visitors.Visitor;

import java.util.List;

public class MoveCommand implements Command{

    private final PainterProgram painter;
    private final List<Figure> figures;
    private final int horizontalDistance;
    private final int verticalDistance;

    /**
     * A command for moving objects around
     * @param painter The painter object to use
     * @param figures The list of figures to move
     * @param horizontalDistance The horizontal distance to move
     * @param verticalDistance The vertical distance to move
     */
    public MoveCommand(PainterProgram painter, List<Figure> figures, int horizontalDistance, int verticalDistance ){
        this.painter = painter;
        this.figures = figures;
        this.horizontalDistance = horizontalDistance;
        this.verticalDistance = verticalDistance;
    }

    @Override
    public void execute(){
        Visitor visitor = new MoveFigureVisitor(horizontalDistance, verticalDistance);
        figures.forEach(figure -> figure.accept(visitor));

        painter.repaint();
    }

    @Override
    public void undo() {
        // Do distance * - 1 to go in opposite direction
        Visitor visitor = new MoveFigureVisitor(horizontalDistance * - 1, verticalDistance * - 1);
        figures.forEach(figure -> figure.accept(visitor));

        painter.repaint();
    }
}
package commands;
import shapes.Shape;
import program.History;
import program.PainterProgram;

public class DrawShapeCommand implements Command{

    private PainterProgram painter;
    private Shape shape;

    public DrawShapeCommand(PainterProgram painter, Shape shape){
        this.painter = painter;
        this.shape = shape;
    }

    @Override
    public void execute(){
        painter.addFigure(shape);
        painter.repaint();
    }

    @Override
    public void undo() {
        painter.removeFigure(shape);
        painter.repaint();
    }

    @Override
    public String getName() {
        return "Draw " + shape.getName();
    }

}

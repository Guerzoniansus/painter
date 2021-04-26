package commands;

import shapes.Figure;
import java.util.List;
import program.PainterProgram;

public class ResizeCommand implements Command {

    private PainterProgram painter;
    private double factor;
    private List<Figure> figures;

    public ResizeCommand(PainterProgram painter, double factor, List<Figure> figures){
        this.painter = painter;
        this.factor = factor;
        this.figures = figures;
    }

    @Override
    public void execute(){
        figures.forEach(figure -> figure.resize(factor));
        painter.repaint();
    }

    @Override
    public void undo() {
        figures.forEach(figure -> figure.resize(factor * -1));
        painter.repaint();
    }
}

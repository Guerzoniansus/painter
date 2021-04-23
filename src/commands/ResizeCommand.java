package commands;

import program.PainterProgram;
import shapes.Figure;
import java.util.List;

public class ResizeCommand implements Command {

    private PainterProgram painter;
    private double factor;
    private List<Figure> Figures;

    public ResizeCommand(PainterProgram painter, double factor, List<Figure> Figures){
        this.painter = painter;
        this.factor = factor;
        this.Figures = Figures;
    }

    @Override
    public void execute(){
        Figures.forEach(figure -> figure.resize(factor));
        painter.repaint();
    }

    @Override
    public void undo() {

    }
}

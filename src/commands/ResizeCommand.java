package commands;

import program.PainterProgram;
import shapes.Figure;
import java.util.List;

public class ResizeCommand implements Command {

    private PainterProgram painter;
    private double factor;
    private List<Figure> selectedFigures;

    public ResizeCommand(PainterProgram painter, double factor, List<Figure> selectedFigures){
        this.painter = painter;
        this.factor = factor;
        this.selectedFigures = selectedFigures;
    }

    @Override
    public void execute(){
        selectedFigures.forEach(figure -> figure.resize(factor));
        painter.repaint();
    }

    @Override
    public void undo() {

    }
}

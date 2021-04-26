package commands;

import shapes.Figure;
import program.History;
import program.PainterProgram;
import java.util.List;

public class MoveCommand implements Command{

    private PainterProgram painter;
    private List<Figure> Figures;
    private int horizontalDistance;
    private int verticalDistance;

    public MoveCommand(PainterProgram painter, List<Figure> Figures, int horizontalDistance, int verticalDistance){
        this.painter = painter;
        this.Figures = Figures;
        this.horizontalDistance = horizontalDistance;
        this.verticalDistance = verticalDistance;
    }

    @Override
    public void execute(){
        Figures.forEach(figure -> figure.move(horizontalDistance, verticalDistance));
        painter.repaint();
    }

    @Override
    public void undo() {
        Figures.forEach(figure -> figure.move(horizontalDistance * -1, verticalDistance * -1));
        painter.repaint();
    }
}
package commands;

import shapes.Figure;
import program.PainterProgram;
import java.util.List;

public class MoveCommand implements Command{

    private PainterProgram painter;
    private List<Figure> Figures;
    private int horizontalDistance;
    private int verticalDistance;

    public MoveCommand(PainterProgram painter, List<Figure> Figures, int horizontalDistance, int verticalDistance){
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

    }
}

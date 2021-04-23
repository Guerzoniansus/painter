package commands;

import shapes.Figure;
import java.util.List;

public class MoveCommand implements Command{
    
    private List<Figure> selectedFigures;
    private int horizontalDistance;
    private int verticalDistance;

    public MoveCommand(List<Figure> selectedFigures, int horizontalDistance, int verticalDistance){
        this.selectedFigures = selectedFigures;
        this.horizontalDistance = horizontalDistance;
        this.verticalDistance = verticalDistance;
    }

    @Override
    public void execute(){
        selectedFigures.forEach(figure -> figure.move(horizontalDistance, verticalDistance));
    }

    @Override
    public void undo() {

    }
}

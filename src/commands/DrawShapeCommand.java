package commands;
import shapes.Figure;
import java.awt.*;
import java.util.List;

public class DrawShapeCommand implements Command{

    private List<Figure> figures;
    private Graphics g;

    public DrawShapeCommand(List<Figure> figures, Graphics g){
        this.figures = figures;
        this.g = g;
    }

    @Override
    public void execute(){
        for (Figure figure : figures) {
            figure.draw(g);
        }
    }

    @Override
    public void undo() {

    }
}

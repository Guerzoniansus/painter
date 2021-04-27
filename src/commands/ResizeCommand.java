package commands;

import shapes.Figure;

import java.util.ArrayList;
import java.util.List;
import program.PainterProgram;
import visitors.ResizeFigureVisitor;

public class ResizeCommand implements Command {

    private final PainterProgram painter;
    private final double factor;
    private final List<Figure> figures;

    /**
     * Create a command for resizing figures
     * @param painter The painter object
     * @param factor The amount to resize the figure. Use a negative number to shrink it.
     * @param figures A list of figures to resize
     */
    public ResizeCommand(PainterProgram painter, double factor, List<Figure> figures){
        this.painter = painter;
        this.factor = factor;
        this.figures = figures;
    }

    /**
     * Create a command for resizing a figure
     * @param painter The painter object
     * @param factor The amount to resize the figure. Use a negative number to shrink it.
     * @param figure The figure to resize
     */
    public ResizeCommand(PainterProgram painter, double factor, Figure figure){
        this.painter = painter;
        this.factor = factor;
        this.figures = new ArrayList<>();
        figures.add(figure);
    }

    @Override
    public void execute(){
        ResizeFigureVisitor visitor = new ResizeFigureVisitor(factor);
        figures.forEach(figure -> figure.accept(visitor));
        painter.repaint();
    }

    @Override
    public void undo() {
        // 1.1 turns into 0.9, 0.5 turns into 2 etc.
        double reverseFactor = 1 / factor;

        ResizeFigureVisitor visitor = new ResizeFigureVisitor(reverseFactor);
        figures.forEach(figure -> figure.accept(visitor));
        painter.repaint();
    }
}

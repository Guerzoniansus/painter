package commands;

import shapes.Figure;

import java.util.ArrayList;
import java.util.List;
import program.PainterProgram;
import visitors.ResizeFigureVisitor;

public class ResizeCommand implements Command {

    private final PainterProgram painter;
    private final int amount;
    private final List<Figure> figures;

    /**
     * Create a command for resizing figures
     * @param painter The painter object
     * @param amount The amount to resize the figure. Use a negative number to shrink it.
     * @param figures A list of figures to resize
     */
    public ResizeCommand(PainterProgram painter, int amount, List<Figure> figures){
        this.painter = painter;
        this.amount = amount;
        this.figures = figures;
    }

    /**
     * Create a command for resizing a figure
     * @param painter The painter object
     * @param amount The amount to resize the figure. Use a negative number to shrink it.
     * @param figure The figure to resize
     */
    public ResizeCommand(PainterProgram painter, int amount, Figure figure){
        this.painter = painter;
        this.amount = amount;
        this.figures = new ArrayList<>();
        figures.add(figure);
    }

    @Override
    public void execute(){
        ResizeFigureVisitor visitor = new ResizeFigureVisitor(amount);
        figures.forEach(figure -> figure.accept(visitor));
        painter.repaint();
    }

    @Override
    public void undo() {
        final int reverseAmount = amount * - 1;

        ResizeFigureVisitor visitor = new ResizeFigureVisitor(reverseAmount);
        figures.forEach(figure -> figure.accept(visitor));
        painter.repaint();
    }

    @Override
    public String getName() {
        return "Resize";
    }
}

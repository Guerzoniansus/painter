package shapes;

import program.PainterProgram;
import visitors.Visitor;

import java.awt.*;
import java.util.List;

public class Group implements Figure {

    private int count;
    private List<Figure> figures;
    private PainterProgram painter;

    public Group(List<Figure> figures, PainterProgram painter) {
        this.painter = painter;
        this.figures = figures;
        this.count = figures.size();
    }

    /**
     * Destroys this group, separating all figures into individual figures again
     */
    public void undoGroup() {
        painter.removeFigure(this);
        figures.forEach(figure -> painter.addFigure(figure));
    }

    /**
     * Returns the amount of figures in this group, not counting ornaments or other groups
     * @return The amount of figures in this group
     */
    public int getCount(){
        return figures.size();
    }

    public List<Figure> getFigures(){
        return figures;
    }

    @Override
    public void draw(Graphics g) {
        figures.forEach(figure -> figure.draw(g));
    }

    @Override
    public void resize(double amount) {
        figures.forEach(figure -> figure.resize(amount));
    }

    @Override
    public void move(int horizontalDistance, int verticalDistance) {
        figures.forEach(figure -> figure.move(horizontalDistance, verticalDistance));
    }

    @Override
    public void drawSelectionBorder(Graphics g) {
        figures.forEach(figure -> figure.drawSelectionBorder(g));
    }

    @Override
    public boolean contains(double x, double y) {
        for (Figure figure : figures) {
            if (figure.contains(x, y)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName(){
        return "group";
    }
}

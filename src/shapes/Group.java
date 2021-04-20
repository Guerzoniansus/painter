package shapes;

import program.PainterProgram;

import java.awt.*;
import java.util.List;

public class Group implements Figure {

    private int count;
    private List<Figure> figures;
    private PainterProgram painter;

    public Group(List<Figure> figures, PainterProgram painter) {
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
    public int getSize() {
        return figures.size();
    }

    @Override
    public void draw(Graphics g) {
        figures.forEach(figure -> figure.draw(g));
    }

    @Override
    public void resize(double factor) {
        figures.forEach(figure -> figure.resize(factor));
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
    public boolean contains(int x, int y) {
        for (Figure figure : figures) {
            if (figure.contains(x, y)) {
                return true;
            }
        }

        return false;
    }
}

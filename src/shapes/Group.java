package shapes;

import visitors.Visitor;

import java.awt.*;
import java.util.List;

public class Group implements Figure {

    private List<Figure> figures;

    public Group(List<Figure> figures) {
        this.figures = figures;
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

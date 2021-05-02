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

    /**
     * Returns the figures in this group
     * @return The figures in this group
     */
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

    @Override 
    public int getX(){
        Shape leftFigure = (Shape)figures.get(0);
        int x = leftFigure.getX();
        for (Figure figure : figures) {
            Shape figur = (Shape)figure;
            if (x > figur.getX()){
                x = figur.getX();
            }
        }
        return x;
    }

    @Override 
    public int getY(){
        Shape highestFigure = (Shape)figures.get(0);
        int y = highestFigure.getY();
        for (Figure figure : figures) {
            Shape figur = (Shape)figure;
            if (y < figur.getY()){
                highestFigure = figur;
            }
        }
        return y;
    }

    @Override 
    public int getWidth(){
        int width = 0;
        for (Figure figure : figures) {
            Shape figur = (Shape)figure;
            width += figur.getWidth();
        }
        return width;
    }

    @Override 
    public int getHeight(){
        int height = 0;
        for (Figure figure : figures) {
            Shape figur = (Shape)figure;
            height += figur.getHeight();
        }
        return height;
    }
}

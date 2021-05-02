package shapes;

import visitors.Visitor;

import java.awt.*;
import java.util.List;

public class Group implements Figure {

    private List<Figure> figures;

    public Group(List<Figure> figures) {
        if (figures.size() == 0) {
            throw new IllegalArgumentException("Groups can not be empty!");
        }
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

    /**
     * Get X of the leftist figure
     * @return X of the leftist figure
     */
    @Override 
    public int getX(){
        Figure leftFigure = figures.get(0);
        int x = leftFigure.getX();
        for (Figure figure : figures) {
            if (x > figure.getX()) {
                leftFigure = figure;
            }
        }
        return x;
    }

    /**
     * Get Y of the highest figure
     * @return Y of the highest figure
     */
    @Override 
    public int getY(){
        Figure highestFigure = figures.get(0);
        int y = highestFigure.getY();
        for (Figure figure : figures) {
            if (y < figure.getY()) {
                highestFigure = figure;
            }
        }
        return y;
    }

    /**
     * Gets the smallest and biggest X and subtracts it, and then adds up the width of the figure width the biggest X
     * @return The sum (width)
     */
    @Override 
    public int getWidth(){
        Figure smallestWidthFigure = figures.get(0);
        Figure biggestWidthFigure = figures.get(0);
        int smallestX = smallestWidthFigure.getX();
        int biggestX = biggestWidthFigure.getX();
        for (Figure figure : figures) {
            if (smallestX > figure.getX()) {
                smallestWidthFigure = figure;
            }
            else if (biggestX < figure.getX()) {
                biggestWidthFigure = figure;
            }
        }
        return ((biggestX - smallestX) + biggestWidthFigure.getWidth());
    }

    /**
     * Gets the smallest and biggest Y and subtracts it, and then adds up the width of the figure width the biggest Y
     * @return The sum (heigth)
     */
    @Override 
    public int getHeight(){
        Figure smallestHeigthFigure = figures.get(0);
        Figure biggestHeigthFigure = figures.get(0);
        int smallestY = smallestHeigthFigure.getY();
        int biggestY = biggestHeigthFigure.getY();
        for (Figure figure : figures) {
            if (smallestY > figure.getY()) {
                smallestHeigthFigure = figure;
            }
            else if (biggestY < figure.getY()) {
                biggestHeigthFigure = figure;
            }
        }
        return ((biggestY - smallestY) + biggestHeigthFigure.getHeight());
    }
}

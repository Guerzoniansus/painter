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
        int smallestX = figures.get(0).getX();

        for (Figure figure : figures) {
            if (figure.getX() < smallestX) {
                smallestX = figure.getX();
            }
        }
        return smallestX;
    }

    /**
     * Get Y of the highest figure
     * @return Y of the highest figure
     */
    @Override 
    public int getY(){
        int smallestY = figures.get(0).getY();

        for (Figure figure : figures) {
            if (figure.getY() < smallestY) {
                smallestY = figure.getY();
            }
        }
        return smallestY;
    }

    /**
     * Gets the smallest and biggest X and subtracts it, and then adds up the width of the figure width the biggest X
     * @return The sum (width = left side of the left figure to the right side of the right figure)
     */
    @Override 
    public int getWidth(){
        int biggestWidth = figures.get(0).getWidth();
        int smallestX = figures.get(0).getX();
        int biggestX = figures.get(0).getX();
        
        for (Figure figure : figures) {
            if (smallestX > figure.getX()) {
                smallestX = figure.getX();
            }
            else if (biggestX < figure.getX()) {
                biggestWidth = figure.getWidth();
                biggestX = figure.getX();
            }
        }
        
        return ((biggestX - smallestX) + biggestWidth);
    }

    /**
     * Gets the smallest and biggest Y and subtracts it, and then adds up the width of the figure width the biggest Y
     * @return The sum (heigth = bottom of the bottom figure to the top of the top figure)
     */
    @Override 
    public int getHeight(){
        int topY = Integer.MAX_VALUE; // Top of the figure
        int height = 0;

        for (Figure figure : figures) {
            if (figure.getY() < topY) {
                topY = figure.getY();
            }

            int outerY = figure.getY() + figure.getHeight(); // Very bottom of the figure
            int heightWithThisFigure = outerY - topY; // From the top of the group to the bottom of this figure

            if (heightWithThisFigure > height) {
                height = heightWithThisFigure;
            }
        }

        return height;
    }
}

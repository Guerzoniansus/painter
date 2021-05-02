package decorator;

import visitors.Visitor;

import java.awt.Graphics;

import shapes.Figure;

public class Ornament extends FigureDecorator{

    public static final String TOP = "top";
    public static final String BOTTOM = "bottom";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";

    private String position;
    private String text;

    public Ornament(String text, String position, Figure decoratedFigure){
        super(decoratedFigure); 
        this.text = text;
        this.position = position;
    }

    @Override
    public void draw(Graphics g) {
        decoratedFigure.draw(g);
    }

    @Override
    public void drawSelectionBorder(Graphics g) {
        decoratedFigure.drawSelectionBorder(g);
    }

    @Override
    public int getX() {
        return decoratedFigure.getX();
    }

    @Override
    public int getY() {
        return decoratedFigure.getY();
    }

    @Override
    public int getWidth() {
        return decoratedFigure.getWidth();
    }

    @Override
    public int getHeight() {
        return decoratedFigure.getHeight();
    }

    @Override
    public boolean contains(double x, double y) {
        return decoratedFigure.contains(x, y);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        return "ornament";
    }
}

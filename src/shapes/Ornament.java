package shapes;

import visitors.Visitor;

import java.awt.Graphics;
import java.util.Vector;


public class Ornament extends FigureDecorator {

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
        drawText(g);
    }

    /**
     * Function to draw this ornament's text
     * @param g The graphics object to draw to
     */
    private void drawText(Graphics g) {
        g.drawString(text, getTextX(getTextWidth(g, text)), getTextY());
    }

    /**
     * Method to determine where the x of the drawn text should be
     * @param textWidth The width of the text to be drawn
     * @return The x coordinate of the drawn text (the left side of the text)
     */
    private int getTextX(int textWidth) {
        // Draw text top left or bottom left
        if (position.equals(TOP) || position.equals(BOTTOM)) {
            return decoratedFigure.getX();
        }

        else {
            int padding = 15;

            // Draw text left of image - text width
            if (position.equals(LEFT)) {
                return decoratedFigure.getX() - textWidth - padding;
            }

            // Draw text right of image
            else if (position.equals(RIGHT)) {
                return decoratedFigure.getX() + decoratedFigure.getWidth() + padding;
            }
        }

        // This is not supposed to happen, but just going to return this in case
        return getX();
    }

    /**
     * Method to determine where the y of the drawn text should be
     * @return The y coordinate of the drawn text
     */
    private int getTextY() {
        // Draw text at half height
        if (position.equals(LEFT) || position.equals(RIGHT)) {
            return decoratedFigure.getY() + (decoratedFigure.getHeight() / 2);
        }

        else {
            if (position.equals(TOP)) {
                int padding = 15;
                return decoratedFigure.getY() - padding;
            }

            else if (position.equals(BOTTOM)){
                int padding = 30;
                return decoratedFigure.getY() + decoratedFigure.getHeight() + padding;
            }
        }

        // This is not supposed to happen, but just going to return this in case
        return decoratedFigure.getY() - 5;
    }

    /**
     * A method to determine the width of a string
     * @param g A graphics object that is needed to calculcate the width
     * @param text The text to calculate the width of
     * @return The width of the given text
     */
    private int getTextWidth(Graphics g, String text) {
        return g.getFontMetrics().stringWidth(text);
    }

    /**
     * Get the position of this ornament's text
     * @return "top", "bottom", "left" or "right"
     */
    public String getPosition() {
        return position;
    }

    /**
     * Get the text of this ornament
     * @return The text of this ornament
     */
    public String getText() {
        return text;
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

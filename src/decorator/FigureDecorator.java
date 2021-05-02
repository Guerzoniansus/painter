package decorator;

import shapes.Figure;

public abstract class FigureDecorator implements Figure {

    protected Figure decoratedFigure;

    FigureDecorator(Figure decoratedFigure){
        this.decoratedFigure = decoratedFigure;
    }

    public Figure getDecoratedFigure() {
        return decoratedFigure;
    }
}
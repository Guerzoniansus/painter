package shapes;

public abstract class FigureDecorator implements Figure {

    protected Figure decoratedFigure;

    FigureDecorator(Figure decoratedFigure){
        this.decoratedFigure = decoratedFigure;
    }

    /**
     * Get DecoratedFigure
     * @return DecoratedFigure
     */
    public Figure getDecoratedFigure() {
        return decoratedFigure;
    }
}
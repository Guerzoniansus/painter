package commands;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import program.FileLoader;
import shapes.Ellipse;
import shapes.Figure;
import shapes.Rectangle;
import shapes.Group;
import program.History;
import program.PainterProgram;
import visitors.SaveFigureVisitor;

public class SaveCommand implements Command{

    private final PainterProgram painter;
    private final List<Figure> figures;
    private final List<Figure> oldFigures;

    public SaveCommand(PainterProgram painter, List<Figure> figures){
        this.painter = painter;
        this.figures = figures;

        // Get figures of the first root group, because they'll be saved as root group again anyway
        oldFigures = ((Group) FileLoader.loadFigures(PainterProgram.SAVE_FILE_PATH).get(0)).getFigures();
    }

    @Override
    public void execute() {
        SaveFigureVisitor saver = new SaveFigureVisitor(PainterProgram.SAVE_FILE_PATH, figures.size());
        figures.forEach(figure -> figure.accept(saver));
        saver.close();
    }

    @Override
    public void undo() {
        SaveFigureVisitor saver = new SaveFigureVisitor(PainterProgram.SAVE_FILE_PATH, oldFigures.size());
        oldFigures.forEach(figure -> figure.accept(saver));
        saver.close();
    }
}


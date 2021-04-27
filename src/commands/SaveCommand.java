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

    /*
    @Override
    public void execute() {
        try {
            if (writer == null){
                writer = new FileWriter(fileName); 
                whiteSpace = 0;
            }
            for (Figure figure : figures){
                if (figure.getName() == "group"){
                    Group group = (Group) figure;
                    if (whiteSpace == 0){
                        String str = (group.getName() + " " + group.getCount() + "\n");
                        writer.append(str);
                        whiteSpace += 5;
                    }
                    else {
                        String str = String.format("%" + whiteSpace + "s", group.getName() + " " + group.getCount() + "\n");
                        writer.append(str);
                    }
                    for (Figure groupChild : group.getFigures()){
                        if (groupChild.getName() == "group"){
                            figures = group.getFigures();
                            whiteSpace += 5;
                            execute();
                        }
                        else if (groupChild.getName() == "rectangle"){
                            Rectangle rectangle = (Rectangle) groupChild;
                            String str = String.format("%" + (whiteSpace + 20) + "s", rectangle.getName() + " " + rectangle.getX() + " " + rectangle.getY() + " " + rectangle.getWidth() + " " + rectangle.getHeight() + "\n");
                            writer.append(str);
                        }
                        else if (groupChild.getName() == "ellipse"){
                            Ellipse ellipse = (Ellipse) groupChild;
                            whiteSpace += 5;
                            String str = String.format("%" + (whiteSpace + 20) + "s", ellipse.getName() + " " + ellipse.getX() + " " + ellipse.getY() + " " + ellipse.getWidth() + " " + ellipse.getHeight() + "\n");
                            writer.append(str);
                        }
                    }
                }
                else if (figure.getName() == "rectangle"){
                    Rectangle rectangle = (Rectangle) figure;
                    writer.append(rectangle.getName() + " " + rectangle.getX() + " " + rectangle.getY() + " " + rectangle.getWidth() + " " + rectangle.getHeight() + "\n");
                }
                else if (figure.getName() == "ellipse"){
                    Ellipse ellipse = (Ellipse) figure;
                    writer.append(ellipse.getName() + " " + ellipse.getX() + " " + ellipse.getY() + " " + ellipse.getWidth() + " " + ellipse.getHeight() + "\n");
                }
            }
            writer.flush();
            writer.close();
            writer = null;
        }
        catch (IOException e) {
            e.printStackTrace();
        } 
    }
    @Override
    public void undo() {
        figures = savedFigures;
        execute();

    }

     */
}


package commands;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import shapes.Ellipse;
import shapes.Figure;
import shapes.Rectangle;
import shapes.Group;

public class SaveCommando implements Command{

    private final String fileName = "resources/Fileio.txt";
    private FileWriter writer;
    private List<Figure> figures;

    public SaveCommando(List<Figure> figures){
        this.figures = figures;
    }

    @Override
    public void execute() {
        try {
            if (writer == null)
            writer = new FileWriter(fileName); 
            for (Figure figure : figures){
                if (figure.getName() == "group"){
                    Group group = (Group) figure;
                    writer.append(group.getName() + " " + group.getCount() + "\n");
                    for (Figure groupChild : group.getFigures()){
                        if (groupChild.getName() == "group"){
                            figures = group.getFigures();
                            execute();
                        }
                        else if (groupChild.getName() == "rectangle"){
                            Rectangle rectangle = (Rectangle) groupChild;
                            writer.append(rectangle.getName() + " " + rectangle.getX() + " " + rectangle.getY() + " " + rectangle.getWidth() + " " + rectangle.getHeight() + "\n");
                        }
                        else if (groupChild.getName() == "ellipse"){
                            Ellipse ellipse = (Ellipse) groupChild;
                            writer.append(ellipse.getName() + " " + ellipse.getX() + " " + ellipse.getY() + " " + ellipse.getWidth() + " " + ellipse.getHeight() + "\n");
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

    }
}


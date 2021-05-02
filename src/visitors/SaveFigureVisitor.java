package visitors;

import shapes.Group;
import shapes.Shape;

import java.io.FileWriter;
import java.io.IOException;

import decorator.Ornament;

public class SaveFigureVisitor implements Visitor {

    private final String fileName;

    private FileWriter writer;

    private String whiteSpace;

    /**
     * Create a visitor for saving figures
     * @param fileName The file name to save to
     */
    public SaveFigureVisitor(String fileName, int amountOfFigures) {
        this.fileName = fileName;

        try {
            writer = new FileWriter(fileName, false);

            // Root group
            writer.write("group " + amountOfFigures);
            writer.write("\n");

            // Set the white space for the next figures
            this.whiteSpace = "  ";
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(Shape shape) {
        try {
            writer.write(whiteSpace + formatShape(shape));
            writer.write("\n");
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(Group group) {
        try {
            writer.write(whiteSpace + "group " + group.getCount());
            writer.write("\n");

            // Add indent, save each figure in the group, then reset the indent
            whiteSpace += "  ";
            group.getFigures().forEach(figure -> figure.accept(this));
            whiteSpace = whiteSpace.substring(0, whiteSpace.length() - 2);
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Properly close the save file
     */
    public void close() {
        try {
            writer.flush();
            writer.close();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Format a shape as string the way it should be saved
     * @param shape The shape to format as string
     * @return The formatted string version of the shape
     */
    private String formatShape(Shape shape) {
        // rectangle 10 20 100 100
        // shape x y width height
        return shape.getName() + " " + shape.getX() +  " " + shape.getY() + " " + shape.getWidth() + " " + shape.getHeight();
    }

    @Override
    public void visit(Ornament ornament) {
        // TODO Auto-generated method stub
    }
}

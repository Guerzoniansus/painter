package commands;

import java.util.ArrayList;
import java.util.List;

import program.FileLoader;
import program.PainterProgram;
import shapes.Figure;

public class LoadCommand implements Command{

    private final String fileName = "resources/Fileio.txt";
    private PainterProgram painter;
    private List<Figure> oldFigures;

    public LoadCommand(PainterProgram painter){
        this.painter = painter;
        this.oldFigures = new ArrayList<>(painter.getFigures());
    }


    @Override
    public void execute() {
        List<Figure> loadedFigures = FileLoader.loadFigures(PainterProgram.SAVE_FILE_PATH);
        painter.getFigures().clear();

        loadedFigures.forEach(figure -> painter.addFigure(figure));
        painter.repaint();
    }


    @Override
    public void undo(){
        painter.getFigures().clear();

        if (oldFigures.size() > 0){
            oldFigures.forEach(figure -> painter.addFigure(figure));
        }

        painter.repaint();
    }

}

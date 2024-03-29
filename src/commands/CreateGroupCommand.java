package commands;

import program.PainterProgram;
import shapes.Figure;
import shapes.Group;
import tools.ToolSelect;
import tools.Tools;

import java.util.List;

public class CreateGroupCommand implements Command {

    private List<Figure> figures;
    private Group group;
    private PainterProgram painter;

    public CreateGroupCommand(PainterProgram painter, List<Figure> figures) {
        this.group = null;
        this.figures = figures;
        this.painter = painter;
    }

    @Override
    public void execute(){
        figures.forEach(figure -> painter.removeFigure(figure));
        group = new Group(figures);
        painter.addFigure(group);   
    }

    @Override
    public void undo() {
        painter.removeFigure(group);
        figures.forEach(figure -> painter.addFigure(figure));
        ((ToolSelect) Tools.TOOL_SELECT).clearSelectedFigures();
    }

    @Override
    public String getName() {
        return "Create Group (" + figures.size() + " figures)";
    }

    /**
     * Get the group that was created by this command's execute command
     * Returns null if no group has been made yet
     * @return The newly created group of figures
     */
    public Group getGroup() {
        return group;
    }

}

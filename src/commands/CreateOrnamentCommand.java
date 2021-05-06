package commands;

import program.PainterProgram;
import shapes.Figure;
import shapes.Ornament;
import tools.ToolOrnament;
import tools.Tools;


public class CreateOrnamentCommand implements Command {

    private PainterProgram painter;
    private Figure decoratedFigure;
    private Ornament ornament;

    public CreateOrnamentCommand(String text, String position, Figure decoratedFigure, PainterProgram painter) {
        this.painter = painter;
        this.decoratedFigure = decoratedFigure;
        ornament = new Ornament(text, position, decoratedFigure);
    }

    @Override
    public void execute() {
        painter.removeFigure(decoratedFigure);
        painter.addFigure(ornament);
        painter.repaint();
    }

    @Override
    public void undo() {
        painter.removeFigure(ornament);
        painter.addFigure(decoratedFigure);
        Tools.TOOL_ORNAMENT.deActivate();
        painter.repaint();
    }

    /**
     * Get the ornament created by this command
     * @return The new ornament
     */
    public Ornament getOrnament() {
        return ornament;
    }

    @Override
    public String getName() {
        return "Create Ornament";
    }
}

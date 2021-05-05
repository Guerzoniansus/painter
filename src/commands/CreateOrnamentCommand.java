package commands;

import program.PainterProgram;
import shapes.Figure;

import javax.swing.*;

public class CreateOrnamentCommand implements Command {

    private Painter painter;
    private Figure decoratedFigure;

    public CreateGroupCommand(String text, String position, Figure decoratedFigure, PainterProgram painter) {
        this.painter = painter;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }

    @Override
    public String getName() {
        return "Create Ornament";
    }
}

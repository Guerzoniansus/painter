package tools;

import program.PainterProgram;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

/**
 * Abstract class for tools used in this program
 */
public abstract class Tool implements MouseListener, MouseMotionListener, MouseWheelListener {

    protected Graphics g;
    protected PainterProgram painter;

    public Tool(PainterProgram painter) {
        this.painter = painter;
        this.g = painter.getGraphics();
    }

    /**
     * Static (constant) tool objects, there's no need to constantly make new objects
     */
    public static Tool TOOL_SELECT;
    public static Tool TOOL_RECTANGLE;
    public static Tool TOOL_ELLIPSE;

    /**
     * Create all tools
     * @param painter The painter program
     */
    public static void createTools(PainterProgram painter) {
        TOOL_SELECT = new ToolSelect(painter);
        TOOL_RECTANGLE = new ToolRectangle(painter);
        TOOL_ELLIPSE = new ToolEllipse(painter);
    }

    /**
     * The function that gets called when the program redraws the screen. Used for painting things
     * @param g The graphics object to draw to
     */
    public abstract void onDraw(Graphics g);

}

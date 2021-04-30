package tools;

import program.PainterProgram;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Abstract class for tools used in this program
 */
public abstract class Tool implements MouseListener, MouseMotionListener {

    protected PainterProgram painter;

    public Tool(PainterProgram painter) {
        this.painter = painter;
    }

    /**
     * The function that gets called when the program redraws the screen. Used for painting things
     * @param g The graphics object to draw to
     */
    public abstract void onDraw(Graphics g);

    /**
     * Function that should get run when this tool gets activated
     */
    public abstract void activate();

    /**
     * Function that should be run when this tool gets deactivated
     */
    public abstract void deActivate();

}

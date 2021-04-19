package program;

import gui.GUI;
import shapes.Figure;
import shapes.Rectangle;
import tools.Tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class PainterProgram extends JPanel implements MouseListener, MouseMotionListener {

    private static String TITLE = "Painter";
    private static int WINDOW_WIDTH = 1300;
    private static int WINDOW_HEIGHT = 975;

    private Window window;

    private List<Figure> figures;

    private GUI gui;

    private Tool currentTool;


    public PainterProgram() {
        window = new Window(TITLE, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        figures = new ArrayList();

        this.setLayout(null);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.setDoubleBuffered(true);

        Tool.createTools(this);
        setActiveTool(Tool.TOOL_SELECT);

        gui = new GUI(this);
        gui.getButtons().forEach(button -> this.add(button));
        gui.updateActiveTool(currentTool);

        repaint();
    }

    /**
     * Set the newly selected tool
     * @param newTool The new tool to select as active
     */
    public void setActiveTool(Tool newTool) {
        if (currentTool != null) {
            this.removeMouseListener(currentTool);
            this.removeMouseMotionListener(currentTool);
            this.removeMouseWheelListener(currentTool);
        }

        currentTool = newTool;
        this.addMouseListener(currentTool);
        this.addMouseMotionListener(currentTool);
        this.addMouseWheelListener(currentTool);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Don't remove this line
        currentTool.onDraw(g);
        drawFigures(g);
    }

    /**
     * Draw all figures
     * @param g The graphics object to draw to
     */
    private void drawFigures(Graphics g) {
        for (Figure figure : figures) {
            figure.draw(g);
        }
    }

    /**
     * Add a figure to the list of figures
     * @param figure The figure to add
     */
    public void addFigure(Figure figure) {
        figures.add(figure);
    }

    /**
     * Return the currently selected tool
     * @return The currently selected tool
     */
    public Tool getActiveTool() {
        return currentTool;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

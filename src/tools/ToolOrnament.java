package tools;

import program.PainterProgram;
import shapes.Figure;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ToolOrnament extends Tool implements KeyListener {

    // Keyboard code
    private static final int ENTER_BUTTON = 10;

    private List<Figure> selectedFigures;

    private double clickX;
    private double clickY;

    private String typedCharacters;

    /**
     * If typing, typing = true, cannot click again before finishing message
     */
    private boolean typing = false;

    /**
     * Creates a tool that can add text to figures
     * @param painter
     */
    public ToolOrnament(PainterProgram painter) {
        super(painter);
        selectedFigures = new ArrayList<>();
        typing = false;
        clickX = 0;
        clickY = 0;
        typedCharacters = "";
    }

    @Override
    public void onDraw(Graphics g) {
        if (typing) {
            g.drawString(typedCharacters, (int) clickX, (int) clickY);
        }
    }

    @Override
    public void activate() {
        painter.addMouseListener(this);
        painter.addKeyListener(this);

        // Get selected figures
        selectedFigures = new ArrayList<>(((ToolSelect) Tools.TOOL_SELECT).getSelectedFigures());
        typing = false;
        clickX = 0;
        clickY = 0;
    }

    @Override
    public void deActivate() {
        painter.removeMouseListener(this);
        painter.removeKeyListener(this);

        selectedFigures.clear();
        typedCharacters = "";
    }

    private void createOrnament() {
        //TODO
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Only do stuff if there are actually any selected figures
        if (selectedFigures.size() < 1)
            return;

        // Mouse click point
        clickX = e.getPoint().getX();
        clickY = e.getPoint().getY();
        typing = true;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        // Only let user type if typing somewhere
        if (typing == false)
            return;


        if (e.getKeyCode() == ENTER_BUTTON) {
            typing = false;
            createOrnament();
        }

        else {
            // Add typed letter to the typed text
            typedCharacters = typedCharacters + Character.toString(e.getKeyChar());
        }

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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

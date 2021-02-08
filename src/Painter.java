import shapes.Figure;
import shapes.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Painter extends JPanel  {

    private static String TITLE = "Painter";
    private static int WINDOW_WIDTH = 1300;
    private static int WINDOW_HEIGHT = 975;

    private Window window;

    private Point selection1, selection2;
    private Point clickPoint;

    private Rectangle selection;

    private boolean dragging;

    private List<Figure> figures;


    public Painter() {
        window = new Window(TITLE, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        figures = new ArrayList();

        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);

        dragging = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Don't remove this line

        drawFiguresa(g);
        showSelection(g);

    }

    private void showSelection(Graphics g) {
        g.fillRect(selection.getX(), selection.getY(), selection.getWidth(), selection.getHeight());
    }

    private void drawFiguresa(Graphics g) {
        for (Figure figure : figures) {
            figure.draw(g);
        }
    }

    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            clickPoint = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            dragging = true;
            selection = determineSelection(clickPoint, e.getPoint());
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            figures.add(selection);

            selection = null;
            dragging = false;
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    };

    private Rectangle determineSelection(Point point1, Point point2) {
        int x = Math.min(point1.x, point2.x);
        int y = Math.min(point1.y, point2.y);

        int width = Math.max(point1.x, point2.x) - x;
        int height = Math.max(point1.y, point2.y) - y;

        return new Rectangle(x, y, width, height);
    }
}

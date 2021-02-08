import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class Window {

    private JFrame frame;

    public Window(String title, int width, int height, JPanel panel) {
        frame = new JFrame(title);
        frame.setSize(width, height);

        frame.setResizable(false);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setBackground(Color.WHITE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);

        frame.setVisible(true);
    }


}

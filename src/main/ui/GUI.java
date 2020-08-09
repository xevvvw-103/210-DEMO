package ui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    // EFFECTS: initialize game UI.
    public GUI() {
        JFrame gui = new JFrame("Workout Puppy");
        gui.setSize(WIDTH, HEIGHT);
        gui.setResizable(false);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setLayout(new BorderLayout());

        JLabel background;
        ImageIcon img = new ImageIcon("./data/tobs.jpg");
        background = new JLabel("", img, JLabel.CENTER);
        gui.add(background, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));
        JButton button;
        JButton button1;
        JButton button2;
        button = new JButton("New Game");
        button1 = new JButton("Resume Game");
        button2 = new JButton("Exist Game");
        panel.add(button);
        panel.add(button1);
        panel.add(button2);
        gui.add(panel, BorderLayout.SOUTH);

        gui.setVisible(true);

    }
}

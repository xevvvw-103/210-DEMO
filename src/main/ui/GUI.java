package ui;

import model.GoldenRetriever;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

public class GUI extends JFrame {
    private static final String DOG_FILE = "./data/dog.json";
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    private GoldenRetriever dog;

    // EFFECTS: initialize game UI.
    public GUI() {
        JFrame gui = getjFrame();

        ImageIcon img = new ImageIcon("./data/tobs.jpg");
        JLabel background = new JLabel("", img, JLabel.CENTER);
        gui.add(background, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));

        JButton button = new JButton("New Game");
        JButton button1 = new JButton("Resume Game");
        JButton button2 = new JButton("Exist Game");
        panel.add(button);
        panel.add(button1);
        panel.add(button2);
        gui.add(panel, BorderLayout.SOUTH);

        gui.setVisible(true);

        addAction(gui, button, button1, button2);
    }

    private void addAction(JFrame gui, JButton button, JButton button1, JButton button2) {
        button2.addActionListener(e -> System.exit(1));

        button.addActionListener(e -> {
            new Prologue();
            init();
            new MainUI();
            gui.dispose();
        });

        button1.addActionListener(e -> {
            new MainUI();
            gui.dispose();
        });
    }

    private JFrame getjFrame() {
        JFrame gui = new JFrame("Workout Puppy");
        gui.setSize(WIDTH, HEIGHT);
        gui.setResizable(false);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setLayout(new BorderLayout());
        return gui;
    }

    private void init() {
        Object[] possibilities = null;
        String s = (String)JOptionPane.showInputDialog(
                null,
                "Please enter your dog's name",
                "Initialize",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                null);
        dog = new GoldenRetriever(s);
        saveGame();
    }

    private void saveGame() {
        try {
            Writer writer = new Writer(new File(DOG_FILE));
            writer.write(dog);
            writer.close();
            System.out.println("\n...");
            System.out.println("Saved");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save game to " + DOG_FILE);
        }
    }

}

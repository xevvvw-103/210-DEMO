package ui;

import model.Recipe;
import model.Recipes;
import org.json.simple.JSONObject;
import persistence.Writer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;

public class DIY extends ConsoleApp {
    private static final String RECIPE_FILE = "./data/recipes.json";
    private static final String DOG_FILE = "./data/dog.json";
    private Recipe newRecipe = new Recipe(0,0,0);

    private static final int WIDTH = 960;
    private static final int HEIGHT = 540;
    private String amount1;
    private String amount2;
    private String amount3;
    private JTextField input1 = new JTextField("0",10);
    private JTextField input2 = new JTextField("0",10);
    private JTextField input3 = new JTextField("0",10);
    private JDialog ui = getjDialog();
    private int cal;
    private JLabel count = new JLabel();

    public DIY() {
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());

        setCenterPanel(ui);

        setNorthPanel(northPanel);

        ui.getContentPane().add(northPanel, BorderLayout.NORTH);

        JPanel southPanel = setSouthPanel();

        ui.getContentPane().add(southPanel, BorderLayout.SOUTH);


    }

    private JPanel setSouthPanel() {
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        JLabel label = new JLabel("Total Calories:");
        label.setFont(new Font("Georgia", Font.BOLD, 18));
        panel1.add(label, BorderLayout.WEST);
        //JLabel count = new JLabel();
        //count.setText(String.valueOf(cal));
        panel1.add(count, BorderLayout.EAST);

        southPanel.add(panel1, BorderLayout.WEST);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        buttonManager(panel2);

        southPanel.add(panel2, BorderLayout.EAST);
        return southPanel;
    }

    private void buttonManager(JPanel panel2) {
        JButton button = new JButton("Preview Calories");
        JButton button1 = new JButton("Save Recipe");
        JButton button2 = new JButton("Feed Dog");
        button.setFont(new Font("Georgia", Font.PLAIN, 14));
        button1.setFont(new Font("Georgia", Font.PLAIN, 14));
        button2.setFont(new Font("Georgia", Font.PLAIN, 14));
        panel2.add(button);
        panel2.add(button1);
        panel2.add(button2);

        button.addActionListener(e -> {
            preview();
            getCalories();
            count.setText(String.valueOf(cal));
        });
        button1.addActionListener(e -> saveRecipe());
        button2.addActionListener(e -> feedDog());
    }

    private void setNorthPanel(JPanel northPanel) {
        JLabel ap = new JLabel("Apple Pie");
        ap.setFont(new Font("Georgia", Font.BOLD, 18));
        northPanel.add(ap);
        northPanel.add(input1);
        northPanel.add(Box.createHorizontalStrut(20));
        JLabel bt = new JLabel("Beef Teriyaki");
        bt.setFont(new Font("Georgia", Font.BOLD, 18));
        northPanel.add(bt);
        northPanel.add(input2);
        northPanel.add(Box.createHorizontalStrut(20));
        JLabel lfm = new JLabel("Low Fat Milk");
        lfm.setFont(new Font("Georgia", Font.BOLD, 18));
        northPanel.add(lfm);
        northPanel.add(input3);
        northPanel.add(Box.createHorizontalStrut(20));

/*        int result = JOptionPane.showConfirmDialog(null, northPanel,
                "Please Enter Amounts For Dishes", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            amount1 = input1.getText();
            amount2 = input2.getText();
            amount3 = input3.getText();
        }*/
    }

    private void setCenterPanel(JDialog ui) {
        JPanel centerPanel = new JPanel();
        ImageIcon img = new ImageIcon("./data/food2.jpg");
        JLabel background = new JLabel("", img, JLabel.CENTER);
        centerPanel.add(background);
        ui.add(centerPanel);
    }

    private JDialog getjDialog() {
        JDialog ui = new JDialog((Dialog) null, "DIY Recipe");
        ui.setSize(WIDTH, HEIGHT);
        ui.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        ui.getContentPane().setLayout(new BorderLayout());
        ui.setVisible(true);
        return ui;
    }

    private void preview() {
        amount1 = input1.getText();
        System.out.println("amount1:" + amount1);
        amount2 = input2.getText();
        System.out.println("amount2:" + amount2);
        amount3 = input3.getText();
        System.out.println("amount3:" + amount3);
    }

    private void getCalories() {
        try {
            int int1 = Integer.parseInt(amount1);
            int int2 = Integer.parseInt(amount2);
            int int3 = Integer.parseInt(amount3);
            System.out.println("int1:" + int1
                    + "int2:" + int2
                    + "int3:" + int3);
            newRecipe = new Recipe(int1, int2, int3);
            cal = newRecipe.calculateCalories();
            System.out.println(cal);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void saveRecipe() {
        Recipes jsonArray = new Recipes();
        JSONObject jsonObject = newRecipe.reToObject();
        jsonArray.addRecipe(jsonObject);

        try {
            Writer writer = new Writer(new File(RECIPE_FILE));
            writer.write(jsonArray);
            writer.close();
            System.out.println("\n...");
            System.out.println("Saved");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save game to " + DOG_FILE);
        }
    }

    private void feedDog() {
        if (newRecipe.calculateCalories() == 0) {
            System.out.println("Design input1 recipe for your dog first.");
        } else {
            ConsoleApp.dog.takeInCalories(newRecipe);
            int i = dog.eatNoWorkout(newRecipe);
            dog.setWeight(dog.weightChange(i));
            System.out.println("Your dog took in "
                    + newRecipe.calculateCalories() + " calories this meal.");
        }
    }
}


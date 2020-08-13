package ui;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import persistence.Reader;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainUI extends ConsoleApp {
    private static final String RECIPE_FILE = "./data/recipes.json";
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;

    public MainUI() {
        loadDoc();

        JFrame gui = getjFrame();

        ImageIcon img = new ImageIcon("./data/puppy-pt.jpg");
        JLabel background = new JLabel("", img, JLabel.CENTER);
        gui.add(background, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));

        JButton button = new JButton("Check Dog's Status");
        JButton button1 = new JButton("Feed Dog");
        JButton button2 = new JButton("Exercise");
        JButton button4 = new JButton("View Saved Recipe");
        JButton button5 = new JButton("Save Game");
        JButton button6 = new JButton("Quit");
        addButton(panel, button, button1, button2, button4, button5, button6);

        gui.add(panel, BorderLayout.SOUTH);

        gui.setVisible(true);

        addAction(button, button1, button2, button4, button5, button6);
    }

    private void addButton(JPanel panel,
                           JButton button,
                           JButton button1,
                           JButton button2,
                           JButton button4,
                           JButton button5,
                           JButton button6) {
        panel.add(button);
        panel.add(button1);
        panel.add(button2);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
    }

    private void addAction(JButton button,
                           JButton button1,
                           JButton button2,
                           JButton button4,
                           JButton button5,
                           JButton button6) {
        button.addActionListener(e -> {
            try {
                specialCaseSolver();
            } catch (Exception exception) {
                String string = dog.getName() + " is seriously overweight, should be sent to "
                        + "animal clinic. GAME OVER.";
                UIManager.put("OptionPane.messageFont",
                        new Font("Georgia", Font.PLAIN, 20));
                int w = 600;
                JOptionPane.showMessageDialog(null, String.format(string,w,w),
                        "Recipe",JOptionPane.PLAIN_MESSAGE);
                new GUI();
            }
        });

        button1.addActionListener(e -> feedADog());

        button2.addActionListener(e -> exerciseDog());

        button4.addActionListener(e -> viewRecipes());

        button5.addActionListener(e -> saveGame());

        button6.addActionListener(e -> System.exit(1));
    }

    private JFrame getjFrame() {
        JFrame gui = new JFrame("Game Interface");
        gui.setSize(WIDTH, HEIGHT);
        gui.setResizable(false);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setLayout(new BorderLayout());
        return gui;
    }

    /*private void loadDoc() {
        try {
            Reader reader = new Reader();
            JSONObject dogObject = (JSONObject) reader.read(new File(DOG_FILE));
            dog = new GoldenRetriever();
            dog.setName((String) dogObject.get("name"));
            dog.setWeight((double) dogObject.get("weight"));
        } catch (IOException | ParseException e) {
            e.getStackTrace();
        }
    }*/

/*    private void checkDogStatus() throws Exception {
        int i = dog.getCaloriesConsumed(newRecipe, workout);
        dog.weightChange(i);
        System.out.println("Your dog : " + dog.getName() + " is " + dog.getWeight() + " Kg now.");
        if (dog.getWeight() >= 38) {
            System.out.println("This little thing should go on diet !");
        } else if (dog.getWeight() >= 30 && dog.getWeight() < 38) {
            System.out.println("This little thing is in health !");
        }
    }*/

/*    private void specialCaseSolver() {
        try {
            checkDogStatus();
        } catch (Exception e) {
            int i = dog.eatNoWorkout(newRecipe);
            dog.weightChange(i);
            System.out.println("Your dog : " + dog.getName() + " is " + dog.getWeight() / 100 * 100 + " Kg now.");
            if (dog.getWeight() >= 38) {
                System.out.println("This little thing should go on diet !");
            } else if (dog.getWeight() >= 30 && dog.getWeight() < 38) {
                System.out.println("This little thing is in health !");
            }
        }
    }*/

/*    private void saveGame() {
        try {
            Writer writer = new Writer(new File(DOG_FILE));
            writer.write(dog);
            writer.close();
            System.out.println("\n...");
            System.out.println("Saved");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save game to " + DOG_FILE);
        }
    }*/

    private void viewRecipes() {
        Reader reader = new Reader();
        try {
            JSONArray recipeObject = (JSONArray) reader.read(new File(RECIPE_FILE));
            JSONObject lastRecipeObject = (JSONObject) recipeObject.get(0);
            JSONObject lastRecipe = (JSONObject) lastRecipeObject.get("recipe");
            System.out.println("Recipe: ");
            System.out.println("Apple Pie * " + lastRecipe.get("AP"));
            System.out.println("Beef Teriyaki * " + lastRecipe.get("BT"));
            System.out.println("Low Fat Milk * " + lastRecipe.get("LFM"));

            String string = "<html><body width='%1s'></h1>Recipe</h1>"
                    + "<p>Apple Pie " + lastRecipe.get("AP")
                    + "<p>Beef Teriyaki " + lastRecipe.get("BT")
                    + "<p>Low Fat Milk " + lastRecipe.get("LFM");
            UIManager.put("OptionPane.messageFont", new Font("Georgia", Font.PLAIN, 20));
            int w = 600;
            JOptionPane.showMessageDialog(null, String.format(string,w,w),
                    "Recipe",JOptionPane.PLAIN_MESSAGE);
        } catch (ParseException | IOException e) {
            System.out.println("\nNo saved recipes.");
            System.out.println("********************");

            JOptionPane.showMessageDialog(null, "No saved recipe", "Exception",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void feedADog() {
        new DIY();
    }

    /*private void exerciseDog() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        System.out.println("\n\nDo you want to walk dog or play with dog ?");
        while (keepGoing) {
            System.out.println("\nPress 'W' for walk dog/ 'P' for play with dog.");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("w")) {
                workout = new WalkDog();
                dog.burnCalories(workout);
                System.out.println("\nAfter input1 long walk, your dog burnt "
                        + workout.getBurntCalories() + " calories.");
                keepGoing = false;
            } else if (command.equals("p")) {
                workout = new PlayWithDog();
                dog.burnCalories(workout);
                System.out.println("You had great fun with your dog, dog burnt "
                        + workout.getBurntCalories() + " calories.");
                keepGoing = false;
            }
        }
    }*/

/*    private void saveRecipe() {
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
    }*/
}

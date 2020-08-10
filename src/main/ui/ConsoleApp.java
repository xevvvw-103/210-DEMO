package ui;

import model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// user interface application
public class ConsoleApp {
    private static final String RECIPE_FILE = "./data/recipes.json";
    private static final String DOG_FILE = "./data/dog.json";
    private GoldenRetriever dog;
    private Scanner input;
    private Recipe newRecipe = new Recipe(0,0,0);
    private Workout workout;

    //EFFECTS: run the console application
    public ConsoleApp() {
        runConsole();
    }

    // MODIFIES: this
    // EFFECTS: process the user input
    private void runConsole() {
        input = new Scanner(System.in);

        System.out.println("Welcome to play this game.");
        System.out.println("**************************");

        loadDoc();
    }

    // MODIFIES: this
    // EFFECTS: loads dog information from DOG_FILE, if that file exists;
    // otherwise initializes game.
    private void loadDoc() {
        try {
            Reader reader = new Reader();
            JSONObject dogObject = (JSONObject) reader.read(new File(DOG_FILE));
            dog = new GoldenRetriever();
            dog.setName((String) dogObject.get("name"));
            dog.setWeight((double) dogObject.get("weight"));
            continuePlay();
        } catch (IOException | ParseException e) {
            init();
        }
    }

    private void init() {
        String command;
        input = new Scanner(System.in);

        System.out.println("\n\nAre you ready to help your little dog become healthy again?");
        System.out.println("\nPress 'Y' to continue playing.");
        System.out.println("Press 'Q' to quit.");

        command = input.next();
        command = command.toLowerCase();

        if (command.equals("y")) {
            setName();
        } else if (command.equals("q")) {
            System.out.println("\nGoodbye!");
            System.exit(1);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "c":
                specialCaseSolver();
                break;
            case "r":
                diyRecipe();
                break;
            case "v":
                viewRecipes();
                break;
            case "f":
                feedDog();
                break;
            case "w":
                exerciseDog();
                break;
            case "s":
                saveGame();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    private void specialCaseSolver() {
        try {
            checkDogStatus();
        } catch (Exception e) {
            int i = dog.eatNoWorkout(newRecipe);
            dog.weightChange(i);
            System.out.println("Your dog : " + dog.getName() + " is " + dog.getWeight() + " Kg now.");
            if (dog.getWeight() >= 38) {
                System.out.println("This little thing should go on diet !");
            } else if (dog.getWeight() >= 30 && dog.getWeight() < 38) {
                System.out.println("This little thing is in health !");
            }
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> Check Dog Status");
        System.out.println("\tr -> DIY recipe");
        System.out.println("\tv -> View saved recipes");
        System.out.println("\tf -> Feed Dog");
        System.out.println("\tw -> Dog Workout");
        System.out.println("\ts -> Save Game");
        System.out.println("\tq -> quit");
    }

    private void checkDogStatus() throws Exception {
        int i = dog.getCaloriesConsumed(newRecipe, workout);
        dog.weightChange(i);
        System.out.println("Your dog : " + dog.getName() + " is " + dog.getWeight() + " Kg now.");
        if (dog.getWeight() >= 38) {
            System.out.println("This little thing should go on diet !");
        } else if (dog.getWeight() >= 30 && dog.getWeight() < 38) {
            System.out.println("This little thing is in health !");
        }
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

    private void diyRecipe() {
        input = new Scanner(System.in);
        System.out.println("\n\nPlease enter the quantities you want. Press Enter to proceed.");
        System.out.println("\n\nApple Pie");
        int a = input.nextInt();
        while (a < 0) {
            System.out.println("Invalid number. Please enter again.");
            a = input.nextInt();
        }
        System.out.println("\nBeef Teriyaki");
        int b = input.nextInt();
        while (b < 0) {
            System.out.println("Invalid number. Please enter again.");
            b = input.nextInt();
        }
        System.out.println("\nLow Fat Milk");
        int c = input.nextInt();
        while (c < 0) {
            System.out.println("Invalid number. Please enter again.");
            c = input.nextInt();
        }
        newRecipe = new Recipe(a, b, c);
        System.out.println("\nThis recipe contains " + newRecipe.calculateCalories() + " calories.");
        doLoop();
    }

    private void doLoop() {
        String command;
        input = new Scanner(System.in);
        System.out.println("\nDo you want to save this recipe ? ");
        System.out.println("Yes[Y]/No[N]");
        command = input.next();
        command = command.toLowerCase();

        if (command.equals("y")) {
            saveRecipe();
        } else if (command.equals("n")) {
            System.out.println("Recipe is not saved.");
        } else {
            doLoop();
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
            continuePlay();
        } catch (ParseException | IOException e) {
            System.out.println("\nNo saved recipes.");
            System.out.println("********************");
            continuePlay();
        }

    }

    private void feedDog() {
        if (newRecipe.calculateCalories() == 0) {
            System.out.println("Design a recipe for your dog first.");
            diyRecipe();
        } else {
            dog.takeInCalories(newRecipe);
            System.out.println("Your dog took in "
                    + newRecipe.calculateCalories() + " calories this meal.");
        }
    }

    private void exerciseDog() {
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
                System.out.println("\nAfter a long walk, your dog burnt "
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
        continuePlay();
    }

    private void setName() {
        String command;
        input = new Scanner(System.in);

        System.out.println("\n\nPlease enter your dog's name.");
        System.out.println("Press 'Enter' to proceed.");
        List<String> name = new ArrayList<>();
        command = input.next();

        name.add(command);
        System.out.println("\nThe dog's name is " + name);
        dog = new GoldenRetriever(name.toString());
        continuePlay();
    }

    private void continuePlay() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }
}

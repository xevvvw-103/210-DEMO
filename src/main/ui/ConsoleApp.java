package ui;

import model.GoldenRetriever;
import model.Recipe;
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
    private Recipe recipe;

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
//            JSONArray recipes = (JSONArray) Reader.read(new File(RECIPE_FILE));
//            JSONObject lastRecipeObject = (JSONObject) recipes.get(recipes.size() - 1);
//            JSONObject lastRecipe = (JSONObject) lastRecipeObject.get("recipe");
//            recipe = new Recipe((int) lastRecipe.get("AP"),
//                    (int) lastRecipe.get("BT"),
//                    (int) lastRecipe.get("LFM"));

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
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "c":
                checkDogStatus();
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

    private void checkDogStatus() {
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

    private void diyRecipe() {}

    private void viewRecipes() {}

    private void feedDog() {}

    private void exerciseDog() {}

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

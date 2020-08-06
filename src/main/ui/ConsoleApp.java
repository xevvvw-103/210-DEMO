package ui;

import model.GoldenRetriever;
import model.Recipe;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import persistence.Reader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// user interface application
public class ConsoleApp {
    private static final String RECIPE_FILE = "./data/recipes.json";
    private static final String DOG_FILE = " ./data/dog.json";
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
        boolean keepGoing = true;
        String command = null;
        input = new Scanner(System.in);

        System.out.println("Welcome to play this game.");

        loadDoc();

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

    // MODIFIES: this
    // EFFECTS: loads dog information from DOG_FILE, if that file exists;
    // otherwise initializes game.
    private void loadDoc() {
        try {
            JSONArray recipes = (JSONArray) Reader.read(new File(RECIPE_FILE));
            JSONObject lastRecipeObject = (JSONObject) recipes.get(recipes.size() - 1);
            JSONObject lastRecipe = (JSONObject) lastRecipeObject.get("recipe");
            recipe = new Recipe((int) lastRecipe.get("AP"),
                    (int) lastRecipe.get("BT"),
                    (int) lastRecipe.get("LFM"));

            JSONObject dogObject = (JSONObject) Reader.read(new File(DOG_FILE));
            dog = new GoldenRetriever((String) dogObject.get("name"),
                    (double) dogObject.get("weight"));
        } catch (IOException | ParseException e) {
            init();
        }
    }

    private void init() {
        System.out.println("Are you ready to help your little dog become healthy again?");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            checkDogStatus();
        } else if (command.equals("r")) {
            diyRecipe();
        } else if (command.equals("v")) {
            viewRecipes();
        } else if (command.equals("f")) {
            feedDog();
        } else if (command.equals("w")) {
            exerciseDog();
        } else if (command.equals("s")) {
            saveGame();
        } else {
            System.out.println("Selection not valid...");
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
    }

    private void diyRecipe() {}

    private void viewRecipes() {}

    private void feedDog() {}

    private void exerciseDog() {}
}

package ui;

import java.util.Scanner;

// user interface application
public class ConsoleApp {
    private static final String ACCOUNTS_FILE = "./data/recipes.txt";
    private Scanner input;

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
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("s")) {
            checkDogStatus();
        } else if (command.equals("r")) {
            diyRecipe();
        } else if (command.equals("v")) {
            viewRecipes();
        } else if (command.equals("f")) {
            feedDog();
        } else if (command.equals("e")) {
            exerciseDog();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> Dog Status");
        System.out.println("\tr -> DIY recipe");
        System.out.println("\tv -> View saved recipes");
        System.out.println("\tf -> Feed Dog");
        System.out.println("\te -> Dog Exercise");
        System.out.println("\tq -> quit");
    }

    private void checkDogStatus() {

    }

    private void diyRecipe() {}

    private void viewRecipes() {}

    private void feedDog() {}

    private void exerciseDog() {}
}

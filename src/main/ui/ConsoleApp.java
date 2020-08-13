package ui;

import exceptions.OverObeseException;
import model.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// user interface application
public class ConsoleApp {
    private static final String DOG_FILE = "./data/dog.json";
    public static GoldenRetriever dog;

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
    void loadDoc() {
        try {
            Reader reader = new Reader();
            JSONObject dogObject = (JSONObject) reader.read(new File(DOG_FILE));
            dog = new GoldenRetriever();
            dog.setName((String) dogObject.get("name"));
            dog.setWeight((double) dogObject.get("weight"));
        } catch (IOException | ParseException e) {
            e.getStackTrace();
        }
    }


    void specialCaseSolver() throws Exception {
        try {
            checkDogStatus();
        } catch (Exception e) {
            int i = dog.eatNoWorkout(newRecipe);
            dog.weightChange(i);
            System.out.println("Your dog : " + dog.getName() + " is "
                    + dog.getWeight() + " Kg now.");
            if (dog.getWeight() >= 38 && dog.getWeight() < 45) {
                System.out.println("This little thing should go on diet !");
            } else if (dog.getWeight() >= 30 && dog.getWeight() < 38) {
                System.out.println("This little thing is in health !");
            } else {
                throw new OverObeseException();
            }
        }
    }

    void checkDogStatus() throws Exception {
        int i = dog.getCaloriesConsumed(newRecipe, workout);
        dog.weightChange(i);
        System.out.println("Your dog : " + dog.getName() + " is "
                + dog.getWeight() + " Kg now.");
        if (dog.getWeight() >= 38) {
            System.out.println("This little thing should go on diet !");
        } else if (dog.getWeight() >= 30 && dog.getWeight() < 38) {
            System.out.println("This little thing is in health !");
        }
    }

    void saveGame() {
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

    void exerciseDog() {
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
    }
}

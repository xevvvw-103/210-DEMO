package ui;

import java.util.Scanner;

// user interface application
public class ConsoleApp {
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
        // press r for design your recipe for puppy.
        // press f for feed puppy with your designed recipe.
        // press w for walk puppy which you could randomly get dishes as a reward.
    }
}

package model;

import exceptions.OverObeseException;

// Represents a golden retriever dog.
public class GoldenRetriever {
    public String name;
    public double weight; // in Kg
    private int caloriesConsumed;
    private static final double C_RATE = 0.001;

    // REQUIRES: weight cannot be negative and between [30-45]kg
    // EFFECTS: creates a golden retriever.
    public GoldenRetriever(String inputString) {
        name = inputString;
        weight = 40;
        caloriesConsumed = 0;
    }

    public int takeInCalories(Recipe recipe) {
        return recipe.calculateCalories();
    }

    public int burnCalories(Workout workout) {
        return workout.getBurntCalories();
    }

    public int getCaloriesConsumed(Recipe recipe, Workout workout) {
        int calorieschange = takeInCalories(recipe) - burnCalories(workout);
        return caloriesConsumed += calorieschange;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double weightChange(int caloriesConsumed) throws OverObeseException {
        weight += caloriesConsumed * C_RATE;
        if (weight >= 45) {
            throw new OverObeseException(name + " is overweight, should be sent to an animal clinic."
                    + " GAME OVER.");
        } else {
            return weight;
        }
    }
}

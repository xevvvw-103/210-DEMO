package model;

import org.json.simple.JSONObject;
import persistence.Savable;

import java.io.PrintWriter;


// Represents a golden retriever dog.
public class GoldenRetriever implements Savable {
    private String name;
    private double weight; // in Kg
    private int caloriesConsumed;
    private static final double C_RATE = 0.001;

    // REQUIRES: weight cannot be negative and between [30-45]kg
    // EFFECTS: creates a golden retriever.
    public GoldenRetriever(String inputString) {
        name = inputString;
        weight = 40;
        caloriesConsumed = 0;
    }

    public GoldenRetriever() {
        caloriesConsumed = 0;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public double setWeight(double d) {
        return this.weight = d;
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

    public double weightChange(int caloriesConsumed) {
        weight += caloriesConsumed * C_RATE;
        if (weight >= 45) {
            System.out.println(name + " is overweight, should be sent to an animal clinic.");
            System.out.println("Game Over.");
            return weight = 40;
        } else {
            return weight;
        }
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.println(grToObject().toJSONString());
    }

    @SuppressWarnings("unchecked")
    public JSONObject grToObject() {
        JSONObject object = new JSONObject();
        object.put("name", this.name);
        object.put("weight", this.weight);
        return object;
    }
}

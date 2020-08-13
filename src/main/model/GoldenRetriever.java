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

    public int eatNoWorkout(Recipe recipe) {
        int i = takeInCalories(recipe);
        return caloriesConsumed += i;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double weightChange(int caloriesConsumed) {
        weight += caloriesConsumed * C_RATE;
        return weight;
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

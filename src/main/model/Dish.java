package model;

// Represents a dish having a name and calories.
public class Dish {
    public String name;
    private final int calories;

    // REQUIRES: Calories cannot be negative.
    // EFFECTS: creates a dish with a name and calories.
    public Dish(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

}

package model;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

// EFFECTS: A recipe is a list of dishes.
public class Recipe {
    public static final Dish AP = new Dish("Apple Pie", 1650);
    public static final Dish BT = new Dish("Beef Teriyaki", 1300);
    public static final Dish LFM = new Dish("Low Fat Milk", 600);

    List<Dish> myRecipe;

    // EFFECTS: creates a empty recipe.
    public Recipe() {
        myRecipe = new ArrayList<>();
    }

    public Recipe(int a, int b, int c) {
        myRecipe = new ArrayList<>();

        for (int i = 0; i < a; i++) {
            addDish(AP);
        }
        for (int i = 0; i < b; i++) {
            addDish(BT);
        }
        for (int i = 0; i < c; i++) {
            addDish(LFM);
        }
    }

    // MODIFIES: this
    // EFFECTS: add a dish to the recipe.
    public void addDish(Dish dish) {
        myRecipe.add(dish);
    }

    // REQUIRES: the recipe is not empty.
    // MODIFIES: this
    // EFFECTS: Removes the first occurrence of the specified element from this list, if it is present.
    public void deleteDish(Dish dish) {
        if (myRecipe.contains(dish)) {
            myRecipe.remove(dish);
            System.out.println(dish.getName() + " " + "has been successfully deleted from the recipe!");
        } else {
            System.out.println("You don't have" + " " + dish.getName() + " " + "in the recipe.");
        }
    }

    // EFFECTS: counts how many times is a dish added to the recipe.
    public int countADish(String name) {
        int i = 0;

        for (Dish dish : myRecipe) {
            if (dish.getName().equals(name)) {
                i++;
            }
        }
        return i;
    }

    // EFFECTS: print out all the dishes in the recipe.
    public void viewRecipe() {
        System.out.println("Apple Pie x" + " " + countADish("Apple Pie"));
        System.out.println("Beef Teriyaki x" + " " + countADish("Beef Teriyaki"));
        System.out.println("Low Fat Milk x" + " " + countADish("Low Fat Milk"));
    }

    // EFFECTS: calculate the total calories contained in the recipe.
    public int calculateCalories() {
        int i = 0;

        for (Dish dish : myRecipe) {
            i += dish.getCalories();
        }
        return i;
    }

    public boolean isEmpty() {
        return myRecipe.size() == 0;
    }

    public int length() {
        return myRecipe.size();
    }

    public JSONObject reToObject() {
        JSONObject object = new JSONObject();
        object.put("AP", countADish("Apple Pie"));
        object.put("BT", countADish("Beef Teriyaki"));
        object.put("LFM", countADish("Low Fat Milk"));

        JSONObject object1 = new JSONObject();
        object1.put("recipe", object);
        return object1;
    }

}

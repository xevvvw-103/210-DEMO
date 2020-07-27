package model;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.LinkedList;

// EFFECTS: A recipe is a list of dishes.
public class Recipe {
    LinkedList<Dish> myRecipe;

    // EFFECTS: creates a empty recipe.
    public Recipe() {
        myRecipe = new LinkedList<Dish>();
    }

    // MODIFIES: this
    // EFFECTS: add a dish to the recipe.
    public boolean addDish(Dish dish) {
        return myRecipe.add(dish);
    }

    // REQUIRES: the recipe is not empty.
    // MODIFIES: this
    // EFFECTS: Removes the first occurrence of the specified element from this list, if it is present.
    public void deleteDish(Dish dish) {
        if (myRecipe.contains(dish)) {
            myRecipe.remove(dish);
            System.out.println(dish.getName() + " " + "has been successfully deleted from the recipe!");;
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
        System.out.println("The recipe has " + i + " calories in total.");
        return i;
    }

    public boolean isEmpty() {
        return myRecipe.size() == 0;
    }

}

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static model.Recipe.*;
import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {
    Recipe recipe;

    @BeforeEach
    public void setup() {
        recipe = new Recipe();
    }

    @Test
    public void constructorTest1() {
        assertTrue(recipe.isEmpty());
    }


    @Test
    public void addDishTest() {
        recipe.addDish(AP);
        assertEquals(AP, recipe.getLastDish());
        assertFalse(recipe.isEmpty());
    }

    @Test
    public void deleteDishTest1() {
        recipe.addDish(BT);
        recipe.deleteDish(AP);
        assertEquals(BT, recipe.getLastDish());
    }

    @Test
    public void deleteDishTest2() {
        recipe.addDish(LFM);
        recipe.deleteDish(LFM);
        assertTrue(recipe.isEmpty());
    }

    @Test
    public void calculateCaloriesTest() {
        recipe.addDish(AP);
        recipe.addDish(AP);
        recipe.addDish(BT);
        recipe.addDish(LFM);
        assertEquals(1650*2+1300+600, recipe.calculateCalories());
    }

    @Test
    public void countADishTest() {
        recipe.addDish(AP);
        recipe.addDish(AP);
        recipe.addDish(BT);
        assertEquals(2, recipe.countADish("Apple Pie"));
    }

    @Test
    public void viewRecipeTest() {
        recipe.addDish(AP);
        recipe.addDish(BT);
        recipe.viewRecipe();
    }
}
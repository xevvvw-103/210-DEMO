package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {
    Recipe recipe;

    @BeforeEach
    public void setup() {
        recipe = new Recipe();
    }

    @Test
    public void constructorTest() {
        assertTrue(recipe.isEmpty());
    }

    @Test
    public void addDishTest() {
        recipe.addDish(Dish.AP);
        recipe.getLastDish().equals(Dish.AP);
    }

    @Test
    public void deleteDishTest1() {
        recipe.addDish(Dish.BT);
        recipe.deleteDish(Dish.AP);
        recipe.getLastDish().equals(Dish.BT);
    }

    @Test
    public void deleteDishTest2() {
        recipe.addDish(Dish.LFM);
        recipe.deleteDish(Dish.LFM);
        assertTrue(recipe.isEmpty());
    }

    @Test
    public void calculateCaloriesTest() {
        recipe.addDish(Dish.AP);
        recipe.addDish(Dish.AP);
        recipe.addDish(Dish.BT);
        recipe.addDish(Dish.LFM);
        assertEquals(1650*2+1300+600, recipe.calculateCalories());
    }
}
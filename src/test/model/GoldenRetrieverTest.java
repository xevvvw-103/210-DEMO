package model;

import exceptions.OverObeseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoldenRetrieverTest {
    GoldenRetriever gr;
    Workout workout;
    Workout workout1;
    Recipe recipe;

    @BeforeEach
    public void setup() {
        workout = new WalkDog();
        workout1 = new PlayWithDog();
        recipe = new Recipe();
        recipe.addDish(Recipe.AP);
        gr = new GoldenRetriever("Pudding");
    }

    @Test
    public void constructorTest() {
        assertEquals("Pudding", gr.getName());
        assertEquals(40, gr.getWeight());
    }

    @Test
    public void getCaloriesConsumedTest() {
        assertTrue(gr.getCaloriesConsumed(recipe, workout) < 0);
    }

    @Test
    public void takeInCaloriesTest() {
        assertEquals(1650, gr.takeInCalories(recipe));
    }

    @Test
    public void burnCaloriesTest() {
        assertTrue(workout.getBurntCalories() >= 2000 && workout.getBurntCalories() <= 2500);
    }

    @Test
    public void weightChangeTest() throws OverObeseException {
        assertEquals(gr.weightChange(-1000), 39.0);
    }

    @Test
    public void weightChangeTest2() {
        gr.weightChange(1000);
    }

    @Test
    public void burnCaloriesTest2() {
        assertTrue(workout1.getBurntCalories() >= 500 && workout1.getBurntCalories() <= 1000);
    }
}


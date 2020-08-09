package model;

import java.util.Random;

public class PlayWithDog extends Workout {
    private int burntCalories;
    private Random random = new Random();

    public PlayWithDog() {
        burntCalories = random.nextInt(501) + 500;
    }

    @Override
    public int getBurntCalories() {
        return burntCalories;
    }
}

package model;

import java.util.Random;

public class WalkDog extends Workout {
    private int burntCalories;
    private Random random = new Random();

    public WalkDog() {
        burntCalories = random.nextInt(501) + 2000;
    }

    @Override
    int getBurntCalories() {
        return burntCalories;
    }
}

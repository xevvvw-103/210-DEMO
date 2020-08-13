package model;

import java.util.Random;

public abstract class Workout {
    private int burntCalories;

    public Workout(int bound, int base) {
        Random random = new Random();
        burntCalories = random.nextInt(bound) + base;
    }

    public int getBurntCalories() {
        return burntCalories;
    }
}

package com.example.drink.drinkmodel;

public class Drink {

    private int dailyGoalAmount;
    private int drinkSize;
    private String notificationInterval;
    private String notificationCooldown;

    public int getDailyGoalAmount() {
        return dailyGoalAmount;
    }

    public void setDailyGoalAmount(int ml) {
        this.dailyGoalAmount = ml;
    }

    public int getDrinkSize() {
        return drinkSize;
    }

    public void setDrinkSize(int ml) {
        this.drinkSize = ml;
    }

    public void drink(int ml) {
        this.dailyGoalAmount += ml;
    }

    public void drink() {
        this.dailyGoalAmount += drinkSize;
    }
}

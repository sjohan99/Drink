package com.example.drink.drinkmodel;

public class Drink {

    private int dailyGoalAmount;
    private int consumedToday;
    private int drinkSize;
    private String measurement;
    private String notificationInterval;
    private int notificationCooldownMinutes;


    public Drink(int dailyGoalAmount, int consumedToday, int drinkSize, String measurement) {
        this.dailyGoalAmount = dailyGoalAmount;
        this.consumedToday = consumedToday;
        this.drinkSize = drinkSize;
        this.measurement = measurement;
        this.notificationInterval = "08:00-22:00";
        this.notificationCooldownMinutes = 120;
    }

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
        this.consumedToday += ml;
    }

    public int getConsumedToday() {
        return consumedToday;
    }

    public void drink() {
        this.consumedToday += drinkSize;
    }
}

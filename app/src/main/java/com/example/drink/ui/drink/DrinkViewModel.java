package com.example.drink.ui.drink;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.drink.R;
import com.example.drink.drinkmodel.Drink;

import java.util.Map;

public class DrinkViewModel extends ViewModel {

    private Drink drink = new Drink(2000, 0, 200, "ml");
    private MutableLiveData<String> consumed;
    private MutableLiveData<String> goal;

    public DrinkViewModel() {
        consumed = new MutableLiveData<>();
        goal = new MutableLiveData<>();
    }

    public LiveData<String> getConsumed() {
        return consumed;
    }

    public LiveData<String> getGoal() {
        return goal;
    }

    public void drink() {
        drink.drink();
        consumed.setValue(drink.getConsumedTodayStr());
    }

    public void updateDrinkAmount(int ml) {
        drink.setDrinkSize(ml);
    }

    public void updateGoal(int ml) {
        drink.setDailyGoal(ml);
    }

    public void initializeDrink(Map<Integer, Integer> values) {
        // Ugly but getOrDefault requires higher API level
        try {
            drink.setDrinkSize(values.get(R.string.saved_drink_size));
        } catch (NullPointerException e) {
            Log.d(TAG, "initializeDrink: value for drink size was null");
            drink.setDrinkSize(200);
        }
        try {
            drink.setDailyGoal(values.get(R.string.saved_daily_goal));
        } catch (NullPointerException e) {
            Log.d(TAG, "initializeDrink: value for drink goal was null");
            drink.setDailyGoal(2500);
        }
        consumed.setValue(drink.getConsumedTodayStr());
        goal.setValue(drink.getDailyGoalStr());
    }

}

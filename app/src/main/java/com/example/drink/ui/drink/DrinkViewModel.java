package com.example.drink.ui.drink;

import static android.content.ContentValues.TAG;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.drink.R;
import com.example.drink.drinkmodel.Date;
import com.example.drink.drinkmodel.Drink;
import com.example.drink.persistency.preferences.PreferenceRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public void saveConsumed(PreferenceRepository repo) {
        repo.saveConsumed(drink.getConsumedToday());
    }

    public void drink(PreferenceRepository repo) {
        drink();
        saveConsumed(repo);
    }

    public void updateDrinkAmount(int ml) {
        drink.setDrinkSize(ml);
    }

    public void updateGoal(int ml) {
        drink.setDailyGoal(ml);
    }

    public void initializeDrink(Map<Integer, Integer> values, PreferenceRepository repo) {
        // Ugly but getOrDefault requires higher API level
        initDrinkSize(values);
        initDailyGoal(values);
        initConsumed(values, repo);
        consumed.setValue(drink.getConsumedTodayStr());
        goal.setValue(drink.getDailyGoalStr());
    }

    private void initDrinkSize(Map<Integer, Integer> values) {
        try {
            drink.setDrinkSize(values.get(R.string.saved_drink_size));
        } catch (NullPointerException e) {
            Log.d(TAG, "initializeDrink: value for drink size was null");
            drink.setDrinkSize(200);
        }
    }

    private void initDailyGoal(Map<Integer, Integer> values) {
        try {
            drink.setDailyGoal(values.get(R.string.saved_daily_goal));
        } catch (NullPointerException e) {
            Log.d(TAG, "initializeDrink: value for drink goal was null");
            drink.setDailyGoal(2500);
        }
    }

    private void initConsumed(Map<Integer, Integer> values, PreferenceRepository repo) {
        if (Date.isNewDay(repo.getLastConsumedDate(), LocalDateTime.now().toString())) {
            repo.saveConsumed(0);
            drink.setConsumedToday(0);
        }
        else {
            try {
                drink.setConsumedToday(values.get(R.string.saved_consumed_today));
            } catch (NullPointerException e) {
                Log.d(TAG, "initializeDrink: value for drink consumed today was null");
                drink.setConsumedToday(0);
            }
        }
    }

}

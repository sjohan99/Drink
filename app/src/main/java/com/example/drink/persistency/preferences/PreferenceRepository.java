package com.example.drink.persistency.preferences;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.drink.R;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class PreferenceRepository {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Activity activity;
    private final static int DRINK_AMOUNT_KEY = R.string.saved_drink_size;
    private final static int DRINK_GOAL_KEY = R.string.saved_daily_goal;
    private final static int DRINK_CONSUMED_TODAY = R.string.saved_consumed_today;
    private final static int DRINK_LAST_CONSUMED = R.string.saved_last_consumed;

    public PreferenceRepository(Activity activity) {
        prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        editor = prefs.edit();
        this.activity = activity;
    }

    public int getDrinkAmount() {
        return Integer.parseInt(prefs.getString(getString(DRINK_AMOUNT_KEY), "200"));
    }

    public int getDrinkGoal() {
        return Integer.parseInt(prefs.getString(getString(DRINK_GOAL_KEY), "2500"));
    }

    public void saveConsumed(int ml) {
        editor.putInt(getString(DRINK_CONSUMED_TODAY), ml).apply();
        editor.putString(getString(DRINK_LAST_CONSUMED), LocalDateTime.now().toString());
    }

    public String getLastConsumedDate() {
        return prefs.getString(getString(DRINK_LAST_CONSUMED), "");
    }

    public int getConsumed() {
        return prefs.getInt(getString(DRINK_CONSUMED_TODAY), 0);
    }

    public Map<Integer, Integer> getAll() {
        HashMap<Integer, Integer> m = new HashMap<>();
        m.put(DRINK_AMOUNT_KEY, getDrinkAmount());
        m.put(DRINK_GOAL_KEY, getDrinkGoal());
        m.put(DRINK_CONSUMED_TODAY, getConsumed());
        return m;
    }

    private String getString(int id) {
        return activity.getString(id);
    }

}

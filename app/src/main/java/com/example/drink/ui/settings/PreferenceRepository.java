package com.example.drink.ui.settings;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.drink.R;

import java.util.HashMap;
import java.util.Map;

public class PreferenceRepository {

    SharedPreferences prefs;
    Activity activity;
    private final static int DRINK_AMOUNT_KEY = R.string.saved_drink_size;
    private final static int DRINK_GOAL_KEY = R.string.saved_daily_goal;

    public PreferenceRepository(Activity activity) {
        prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        this.activity = activity;
    }

    public int getDrinkAmount() {
        return Integer.parseInt(prefs.getString(getString(DRINK_AMOUNT_KEY), "200"));
    }

    public int getDrinkGoal() {
        return Integer.parseInt(prefs.getString(getString(DRINK_GOAL_KEY), "2500"));
    }

    public Map<Integer, Integer> getAll() {
        HashMap<Integer, Integer> m = new HashMap<>();
        m.put(DRINK_AMOUNT_KEY, getDrinkAmount());
        m.put(DRINK_GOAL_KEY, getDrinkGoal());
        return m;
    }

    private String getString(int id) {
        return activity.getString(id);
    }

}

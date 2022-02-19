package com.example.drink.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;

import com.example.drink.R;
import com.example.drink.ui.drink.DrinkViewModel;
import com.takisoft.preferencex.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private SharedPreferences.OnSharedPreferenceChangeListener listener;
    private DrinkViewModel drinkViewModel;

    @Override
    public void onCreatePreferencesFix(@Nullable Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(requireActivity());
        editor = sharedPref.edit();
        drinkViewModel = new ViewModelProvider(requireActivity()).get(DrinkViewModel.class);
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences prefs, String s) {
                if (fragmentAttached() && s.equals(getString(R.string.saved_drink_size))) {
                    drinkViewModel.updateDrinkAmount(Integer.parseInt(prefs.getString(s, "200")));
                }
                if (fragmentAttached() && s.equals(getString(R.string.saved_daily_goal))) {
                    drinkViewModel.updateGoal(Integer.parseInt(prefs.getString(s, "2500")));
                }
            }
        };
        sharedPref.registerOnSharedPreferenceChangeListener(listener);
    }

    private boolean fragmentAttached() {
        return getContext() != null;
    }
}

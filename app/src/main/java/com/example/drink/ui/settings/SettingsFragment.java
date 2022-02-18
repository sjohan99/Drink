package com.example.drink.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.example.drink.R;
import com.example.drink.ui.drink.DrinkViewModel;

import java.util.Map;

public class SettingsFragment extends PreferenceFragmentCompat {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private SharedPreferences.OnSharedPreferenceChangeListener listener;
    private DrinkViewModel drinkViewModel;

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(requireActivity());
        editor = sharedPref.edit();
        drinkViewModel = new ViewModelProvider(requireActivity()).get(DrinkViewModel.class);
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences prefs, String s) {
                if (fragmentAttached() && s.equals(getString(R.string.saved_drink_amount))) {
                    drinkViewModel.updateDrinkAmount(Integer.parseInt(prefs.getString(s, "200")));
                }
            }
        };
        sharedPref.registerOnSharedPreferenceChangeListener(listener);
    }

    private boolean fragmentAttached() {
        return getContext() != null;
    }
}

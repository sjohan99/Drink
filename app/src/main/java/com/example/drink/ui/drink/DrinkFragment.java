package com.example.drink.ui.drink;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.drink.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class DrinkFragment extends Fragment {

    private FragmentHomeBinding binding;
    private DrinkViewModel drinkViewModel;
    TextView consumed;
    ExtendedFloatingActionButton drinkButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        drinkViewModel = new ViewModelProvider(this).get(DrinkViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        consumed = binding.drunkenTodayText;
        drinkButton = binding.drinkButton;
        drinkViewModel.getConsumedOfTotal().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                consumed.setText(s);
            }
        });

        drinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drinkViewModel.drink();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
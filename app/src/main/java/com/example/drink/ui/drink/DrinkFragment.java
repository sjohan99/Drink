package com.example.drink.ui.drink;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.drink.R;
import com.example.drink.databinding.FragmentDrinkBinding;
import com.example.drink.persistency.preferences.PreferenceRepository;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class DrinkFragment extends Fragment {

    private FragmentDrinkBinding binding;
    private DrinkViewModel drinkViewModel;
    private TextView consumed, goal;
    private ExtendedFloatingActionButton drinkButton;
    private Animation animBounce;
    private PreferenceRepository repo;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        drinkViewModel = new ViewModelProvider(requireActivity()).get(DrinkViewModel.class);

        binding = FragmentDrinkBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        repo = new PreferenceRepository(requireActivity());
        drinkViewModel.initializeDrink(repo.getAll(), repo);

        consumed = binding.drunkenTodayText;
        goal = binding.goalAmountText;
        drinkButton = binding.drinkButton;
        animBounce = AnimationUtils.loadAnimation(requireContext().getApplicationContext(), R.anim.bounce);

        drinkViewModel.getConsumed().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                consumed.startAnimation(animBounce);
                consumed.setText(s);
            }
        });

        drinkViewModel.getGoal().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                goal.setText(s);
            }
        });

        drinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drinkViewModel.drink(repo);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        drinkViewModel.saveConsumed(repo);
        super.onDestroyView();
        binding = null;
    }
}
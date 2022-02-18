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
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class DrinkFragment extends Fragment {

    private FragmentDrinkBinding binding;
    private DrinkViewModel drinkViewModel;
    private TextView consumed;
    private ExtendedFloatingActionButton drinkButton;
    private Animation animBounce;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        drinkViewModel = new ViewModelProvider(requireActivity()).get(DrinkViewModel.class);

        binding = FragmentDrinkBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        consumed = binding.drunkenTodayText;
        drinkButton = binding.drinkButton;
        animBounce = AnimationUtils.loadAnimation(requireContext().getApplicationContext(), R.anim.bounce);

        drinkViewModel.getConsumed().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                consumed.startAnimation(animBounce);
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
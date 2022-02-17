package com.example.drink.ui.drink;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.drink.drinkmodel.Drink;

public class DrinkViewModel extends ViewModel {

    private Drink drink = new Drink(2000, 0, 200, "ml");
    private MutableLiveData<String> consumedOfTotal;

    public DrinkViewModel() {
        consumedOfTotal = new MutableLiveData<>();
        consumedOfTotal.setValue(getFormattedAmount());
    }

    private String getFormattedAmount() {
        return drink.getConsumedToday() + " / " + drink.getDailyGoalAmount();
    }

    public LiveData<String> getConsumedOfTotal() {
        return consumedOfTotal;
    }

    public void drink() {
        drink.drink();
        consumedOfTotal.setValue(getFormattedAmount());
    }

}

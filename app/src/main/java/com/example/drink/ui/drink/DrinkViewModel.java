package com.example.drink.ui.drink;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.drink.drinkmodel.Drink;

public class DrinkViewModel extends ViewModel {

    private Drink drink = new Drink(2000, 0, 200, "ml");
    private MutableLiveData<String> consumed;

    public DrinkViewModel() {
        consumed = new MutableLiveData<>();
        consumed.setValue(drink.getConsumedTodayStr());
    }

    public LiveData<String> getConsumed() {
        return consumed;
    }

    public void drink() {
        drink.drink();
        consumed.setValue(drink.getConsumedTodayStr());
    }

}

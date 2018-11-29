package com.yts.tsdiet.viewmodel;

import android.view.View;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.data.model.Food;

public class FoodViewModel extends BaseViewModel {
    public TSLiveData<Food> mFood = new TSLiveData<>();

    public void setFood(Food food) {
        mFood.setValue(food);
    }
}

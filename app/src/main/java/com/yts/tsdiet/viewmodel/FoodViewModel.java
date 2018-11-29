package com.yts.tsdiet.viewmodel;


import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.data.model.Food;
import com.yts.tsdiet.utils.SendBroadcast;

public class FoodViewModel extends BaseViewModel {
    public TSLiveData<Food> mFood = new TSLiveData<>();

    public void setFood(Food food) {
        mFood.setValue(food);
    }

    public void selectFood(View view) {
        Context context = view.getContext();
        SendBroadcast.selectFood(context, mFood.getValue());
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }
}

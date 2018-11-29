package com.yts.tsdiet.viewmodel;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.data.model.Food;
import com.yts.tsdiet.data.model.RecordFood;

public class RecordFoodViewModel extends BaseViewModel {

    public TSLiveData<Food> mFood = new TSLiveData<>();
    public TSLiveData<RecordFood> mRecordFood = new TSLiveData<>();


    public void setFood(Food food) {
        mFood.setValue(food);

        RecordFood recordFood = new RecordFood(food.getName(), food.getSize(), food.getKcal(), food.getCarbohydrate(), food.getProtein(), food.getFat(),
                food.getSugars(), food.getSalt(), food.getCholesterol(), food.getSaturated(), food.getTrans());

        mRecordFood.setValue(recordFood);
    }
}

package com.yts.tsdiet.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.data.model.Food;
import com.yts.tsdiet.data.model.RecordFood;
import com.yts.tsdiet.utils.SendBroadcast;

public class RecordFoodViewModel extends BaseViewModel {

    public TSLiveData<Food> mFood = new TSLiveData<>();
    public TSLiveData<RecordFood> mRecordFood = new TSLiveData<>();

    public TSLiveData<Double> mQuantity = new TSLiveData<>(1.0);
    public TSLiveData<Double> mSize = new TSLiveData<>(1.0);


    public TSLiveData<Integer> mPosition = new TSLiveData<>(0);

    public void setPosition(int position) {
        mPosition.setValue(position);
    }


    public void setFood(Food food) {
        mFood.setValue(food);

        RecordFood recordFood = new RecordFood(food.getName(), food.getSize(), food.getKcal(), food.getCarbohydrate(), food.getProtein(), food.getFat(),
                food.getSugars(), food.getSalt(), food.getCholesterol(), food.getSaturated(), food.getTrans());
        mSize.setValue(food.getSize());
        mRecordFood.setValue(recordFood);
    }

    public void setRecordFood(RecordFood recordFood) {
        mRecordFood.setValue(recordFood);
    }

    public void changeQuantity(CharSequence charSequence) {
        double quantity = 0.0;

        try {
            quantity = Double.parseDouble(charSequence.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setQuantity(quantity);
    }

    public void changeSize(CharSequence charSequence) {
        double size = 0.0;

        try {
            size = Double.parseDouble(charSequence.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setSize(size);
    }

    public void setQuantity(double quantity) {
        if (mFood.getValue() != null && mRecordFood.getValue() != null) {
            Food food = mFood.getValue();
            RecordFood recordFood = mRecordFood.getValue();
            if (quantity != recordFood.getQuantity()) {
                recordFood.setQuantity(quantity);

                double split = food.getKcal() / food.getSize();
                double kcal = split * recordFood.getSize() * recordFood.getQuantity();

                double carbohydrate = (food.getCarbohydrate() / food.getSize()) * recordFood.getSize() * recordFood.getQuantity();
                double protein = (food.getProtein() / food.getSize()) * recordFood.getSize() * recordFood.getQuantity();
                double fat = (food.getFat() / food.getSize()) * recordFood.getSize() * recordFood.getQuantity();

                recordFood.setKcal(kcal);
                recordFood.setCarbohydrate(carbohydrate);
                recordFood.setProtein(protein);
                recordFood.setFat(fat);
                mRecordFood.setValue(recordFood);
            }
        }
    }

    public void setSize(double size) {
        if (mFood.getValue() != null && mRecordFood.getValue() != null) {
            Food food = mFood.getValue();
            RecordFood recordFood = mRecordFood.getValue();
            if (size != recordFood.getSize()) {
                recordFood.setSize(size);

                double split = food.getKcal() / food.getSize();
                double kcal = split * recordFood.getSize() * recordFood.getQuantity();

                double carbohydrate = (food.getCarbohydrate() / food.getSize()) * recordFood.getSize() * recordFood.getQuantity();
                double protein = (food.getProtein() / food.getSize()) * recordFood.getSize() * recordFood.getQuantity();
                double fat = (food.getFat() / food.getSize()) * recordFood.getSize() * recordFood.getQuantity();

                recordFood.setKcal(kcal);
                recordFood.setCarbohydrate(carbohydrate);
                recordFood.setProtein(protein);
                recordFood.setFat(fat);
                mRecordFood.setValue(recordFood);
            }
        }
    }

    public void save(View view) {
        Context context = view.getContext();
        SendBroadcast.saveRecordFood(context, mRecordFood.getValue());
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
    }

    public void remove(View view) {
        Context context = view.getContext();
        SendBroadcast.removeRecordFood(context, mRecordFood.getValue(), mPosition.getValue());
    }
}

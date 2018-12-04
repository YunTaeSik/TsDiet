package com.yts.tsdiet.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.data.model.Record;
import com.yts.tsdiet.ui.activity.FoodListActivity;

public class RecordViewModel extends BaseViewModel {
    public TSLiveData<Record> mRecord = new TSLiveData<>();

    public void setRecord(Record record) {
        mRecord.setValue(record);
    }

    public void startFoodList(View view) {
        Context context = view.getContext();
        Intent foodList = new Intent(context, FoodListActivity.class);
        context.startActivity(foodList);
    }
}

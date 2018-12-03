package com.yts.tsdiet.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.data.model.Record;
import com.yts.tsdiet.data.model.RecordFood;
import com.yts.tsdiet.realm.RealmService;
import com.yts.tsdiet.ui.activity.FoodListActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class RecordListViewModel extends BaseViewModel {
    public TSLiveData<List<Object>> mRecordList = new TSLiveData<>();


    private void setRecordList(Record record) {
        ArrayList<Object> recordList = new ArrayList<>();
        recordList.add(record);
        if (record.getRecordFoodList() != null) {
            recordList.addAll(record.getRecordFoodList());
        }
        mRecordList.setValue(recordList);
    }

    public void getRecordList(Realm realm, int year, int month, int day) {
        Record record = RealmService.getRecord(realm, year, month, day);
        setRecordList(record);
    }


    public void addRecordFood(RecordFood recordFood) {
        if (mRecordList.getValue() != null) {
            List<Object> recordList = mRecordList.getValue();
            if (recordList.size() > 0) {
                Record record = (Record) recordList.get(0);
                record.addRecordFood(recordFood);
                setRecordList(record);
            }
        }
    }

    public void startFoodList(View view) {
        Context context = view.getContext();
        Intent foodList = new Intent(context, FoodListActivity.class);
        context.startActivity(foodList);

    }
}

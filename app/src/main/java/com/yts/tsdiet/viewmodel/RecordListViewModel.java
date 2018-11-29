package com.yts.tsdiet.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.data.model.Record;
import com.yts.tsdiet.realm.RealmService;
import com.yts.tsdiet.ui.activity.FoodListActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class RecordListViewModel extends BaseViewModel {
    public TSLiveData<List<Object>> mRecordList = new TSLiveData<>();

    public void setRecordList(Realm realm, int year, int month, int day) {
        ArrayList<Object> recordList = new ArrayList<>();

        Record record = RealmService.getRecord(realm, year, month, day);

        recordList.add(record);
        if (record.getRecordFoodList() != null) {
            recordList.addAll(record.getRecordFoodList());
        }
        mRecordList.setValue(recordList);
    }

    public void startFoodList(View view) {
        Context context = view.getContext();
        Intent foodList = new Intent(context, FoodListActivity.class);
        context.startActivity(foodList);

    }
}

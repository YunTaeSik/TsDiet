package com.yts.tsdiet.viewmodel;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.data.model.Record;
import com.yts.tsdiet.realm.RealmService;

import java.util.ArrayList;
import java.util.List;

public class RecordListViewModel extends BaseViewModel {
    public TSLiveData<List<Object>> mRecordList = new TSLiveData<>();

    public void setRecordList(int year, int month, int day) {
        ArrayList<Object> recordList = new ArrayList<>();

        Record record = RealmService.getRecord(year, month, day);

        recordList.add(record);
        if (record.getRecordFoodList() != null) {
            recordList.addAll(record.getRecordFoodList());
        }
        mRecordList.setValue(recordList);
    }
}

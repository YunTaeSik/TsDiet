package com.yts.tsdiet.viewmodel;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.data.model.Record;

public class RecordViewModel extends BaseViewModel {
    public TSLiveData<Record> mRecord = new TSLiveData<>();

    public void setRecord(Record record) {
        mRecord.setValue(record);
    }
}

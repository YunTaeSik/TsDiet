package com.yts.tsdiet.viewmodel.chart;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.data.model.Record;
import com.yts.tsdiet.realm.RealmService;
import com.yts.tsdiet.viewmodel.BaseViewModel;

import java.util.List;

import io.realm.Realm;

public class ChartViewModel extends BaseViewModel {
    public TSLiveData<List<Record>> mRecordList = new TSLiveData<>();

    public TSLiveData<Integer> mDateRangeType = new TSLiveData<>(1);

    public void initData() {
        setDateRange(1);
    }

    public void refreshData() {
        mRecordList.setValue(RealmService.getFastRecordList(Realm.getDefaultInstance(), mDateRangeType.getValue()));
    }

    public void setDateRange(int value) {
        mDateRangeType.setValue(value);
        mRecordList.setValue(RealmService.getFastRecordList(Realm.getDefaultInstance(), value));
    }


}

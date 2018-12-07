package com.yts.tsdiet.viewmodel.chart;

import android.view.View;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.data.model.Record;
import com.yts.tsdiet.realm.RealmService;
import com.yts.tsdiet.ui.dialog.DateRangeDialog;
import com.yts.tsdiet.viewmodel.BaseViewModel;

import java.util.List;

import io.realm.Realm;

public class ChartViewModel extends BaseViewModel {
    public TSLiveData<List<Record>> mRecordList = new TSLiveData<>();

    public TSLiveData<Integer> mDateRangeType = new TSLiveData<>(1);

    public void initData() {
        mDateRangeType.setValue(1);
        mRecordList.setValue(RealmService.getFastRecordList(Realm.getDefaultInstance()));
    }

    public void setDateRange(int value) {
        mDateRangeType.setValue(value);
    }


}

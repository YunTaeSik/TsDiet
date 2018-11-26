package com.yts.tsdiet.viewmodel;

import com.yts.tsdiet.data.TSLiveData;

public class CalendarHeaderViewModel extends BaseViewModel {
    public TSLiveData<Long> mHeaderDate = new TSLiveData<>();

    public void setHeaderDate(long headerDate) {
        this.mHeaderDate.setValue(headerDate);
    }
}

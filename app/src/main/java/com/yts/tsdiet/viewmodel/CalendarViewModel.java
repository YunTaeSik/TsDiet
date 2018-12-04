package com.yts.tsdiet.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.data.model.Record;
import com.yts.tsdiet.realm.RealmService;
import com.yts.tsdiet.ui.activity.RecordActivity;
import com.yts.tsdiet.utils.Keys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import io.realm.Realm;

public class CalendarViewModel extends BaseViewModel {
    public TSLiveData<Calendar> mCalendar = new TSLiveData<>();
    public TSLiveData<ArrayList<Object>> mDayList = new TSLiveData<>();

    public TSLiveData<Record> mRecord = new TSLiveData<>();

    public void setCalendar(Calendar calendar) {
        this.mCalendar.setValue(calendar);
    }

    public void setDayList(Calendar calendar) {
        ArrayList<Object> dayList = new ArrayList<>();

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < dayOfWeek; i++) {
            dayList.add(Keys.EMPTY);
        }
        for (int i = 1; i <= max; i++) {
            dayList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), i));
        }
        mDayList.setValue(dayList);
    }

    public void setRecord() {
        Calendar calendar = mCalendar.getValue();
        Record record = RealmService.getFastRecord(Realm.getDefaultInstance(), calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        mRecord.setValue(record);
    }

    public void startRecord(View view) {
        Context context = view.getContext();
        Intent record = new Intent(context, RecordActivity.class);
        record.putExtra(Keys.CALENDAR, mCalendar.getValue());
        context.startActivity(record);
    }
}

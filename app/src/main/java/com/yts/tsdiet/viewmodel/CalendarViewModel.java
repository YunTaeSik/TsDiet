package com.yts.tsdiet.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.data.model.Record;
import com.yts.tsdiet.realm.RealmService;
import com.yts.tsdiet.ui.activity.RecordActivity;
import com.yts.tsdiet.utils.Keys;
import com.yts.tsdiet.utils.SharedPrefsUtils;

import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;

public class CalendarViewModel extends BaseViewModel {
    public TSLiveData<Calendar> mCalendar = new TSLiveData<>();

    public TSLiveData<Record> mRecord = new TSLiveData<>();

    public TSLiveData<Double> mGoalWeight = new TSLiveData<>(0.0);
    public TSLiveData<Double> mGoalKcal = new TSLiveData<>(0.0);

    public void initGoal(Context context) {
        double goalWeight = SharedPrefsUtils.getFloatPreference(context, Keys.GOAL_WEIGHT, 0);
        double goalKcal = SharedPrefsUtils.getFloatPreference(context, Keys.GOAL_KCAL, 0);
        mGoalWeight.setValue(goalWeight);
        mGoalKcal.setValue(goalKcal);
    }

    public void setCalendar(Calendar calendar) {
        this.mCalendar.setValue(calendar);
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

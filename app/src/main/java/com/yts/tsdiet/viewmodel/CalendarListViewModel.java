package com.yts.tsdiet.viewmodel;

import com.yts.tsdiet.data.TSLiveData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarListViewModel extends BaseViewModel {
    public TSLiveData<ArrayList<Object>> mCalendarList = new TSLiveData<>(new ArrayList<>());

    public void setCalendarList(GregorianCalendar calendar) {
        ArrayList<Object> calendarList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            calendarList.add(calendar.getTimeInMillis());
            calendarList.add(calendar);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        mCalendarList.setValue(calendarList);
    }
}

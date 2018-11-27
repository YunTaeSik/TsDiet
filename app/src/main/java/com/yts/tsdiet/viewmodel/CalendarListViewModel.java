package com.yts.tsdiet.viewmodel;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.utils.DateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarListViewModel extends BaseViewModel {
    public TSLiveData<String> mTitle = new TSLiveData<>();
    public TSLiveData<ArrayList<Object>> mCalendarList = new TSLiveData<>(new ArrayList<>());

    public void setTitle(int position) {
        try {
            Object item = mCalendarList.getValue().get(position);
            if (item instanceof Long) {
                mTitle.setValue(DateFormat.getDate((Long) item, DateFormat.CALENDAR_HEADER_FORMAT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCalendarList(GregorianCalendar cal) {
        ArrayList<Object> calendarList = new ArrayList<>();
        for (int i = -300; i < 300; i++) {
            try {
                GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i, 1, 0, 0, 0);
                calendarList.add(calendar.getTimeInMillis());
                calendarList.add(calendar);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mCalendarList.setValue(calendarList);
    }

}

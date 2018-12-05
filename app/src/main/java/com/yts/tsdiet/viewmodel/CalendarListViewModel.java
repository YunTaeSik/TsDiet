package com.yts.tsdiet.viewmodel;

import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.utils.DateFormat;
import com.yts.tsdiet.utils.Keys;

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

    public void initCalendarList() {
        GregorianCalendar cal = new GregorianCalendar();
        mTitle.setValue(DateFormat.getDate(System.currentTimeMillis(), DateFormat.CALENDAR_HEADER_FORMAT));

        ArrayList<Object> calendarList = new ArrayList<>();
        for (int i = -300; i < 300; i++) {
            try {
                GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i, 1, 0, 0, 0);
                calendarList.add(calendar.getTimeInMillis());

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                for (int j = 0; j < dayOfWeek; j++) {
                    calendarList.add(Keys.EMPTY);
                }
                for (int j = 1; j <= max; j++) {
                    calendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), j));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mCalendarList.setValue(calendarList);
    }

}

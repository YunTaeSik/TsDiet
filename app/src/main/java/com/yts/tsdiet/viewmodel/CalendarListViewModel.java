package com.yts.tsdiet.viewmodel;

import android.content.Context;
import android.view.View;

import com.whiteelephant.monthpicker.MonthPickerDialog;
import com.yts.tsdiet.data.TSLiveData;
import com.yts.tsdiet.utils.DateFormat;
import com.yts.tsdiet.utils.Keys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.lifecycle.MutableLiveData;

public class CalendarListViewModel extends BaseViewModel {
    private long mCurrentTime;

    public TSLiveData<String> mTitle = new TSLiveData<>();
    public TSLiveData<ArrayList<Object>> mCalendarList = new TSLiveData<>(new ArrayList<>());

    public int mCenterPosition;

    public void setTitle(int position) {
        try {
            Object item = mCalendarList.getValue().get(position);
            if (item instanceof Long) {
                setTitle((Long) item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTitle(long time) {
        mCurrentTime = time;
        mTitle.setValue(DateFormat.getDate(time, DateFormat.CALENDAR_HEADER_FORMAT));
    }


    public void initCalendarList() {
        GregorianCalendar cal = new GregorianCalendar();
        setCalendarList(cal);
    }

    public void setCalendarList(GregorianCalendar cal) {


        setTitle(cal.getTimeInMillis());

        ArrayList<Object> calendarList = new ArrayList<>();
        for (int i = -300; i < 300; i++) {
            try {
                GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i, 1, 0, 0, 0);
                if (i == 0) {
                    mCenterPosition = calendarList.size();
                }
                calendarList.add(calendar.getTimeInMillis());

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; //해당 월에 시작하는 요일 -1 을 하면 빈칸을 구할 수 있겠죠 ?
                int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 해당 월에 마지막 요일

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


    public void startPicker(View view) {
        Context context = view.getContext();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mCurrentTime);

        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(context, new MonthPickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(int selectedMonth, int selectedYear) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(selectedYear, selectedMonth, 1);
                setCalendarList(gregorianCalendar);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));

        builder
                .setMaxYear(calendar.get(Calendar.YEAR) + 100)
                .build().show();
    }
}

package com.yts.tsdiet.bindingAdapter;

import android.widget.TextView;

import com.yts.tsdiet.utils.DateFormat;

import java.util.Calendar;

import androidx.databinding.BindingAdapter;

public class TextBindingAdapter {
    @BindingAdapter({"setCalendarHeaderText"})
    public static void setCalendarHeaderText(TextView view, Long date) {
        try {
            if (date != null) {
                view.setText(DateFormat.getDate(date, DateFormat.CALENDAR_HEADER_FORMAT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setDayText"})
    public static void setDayText(TextView view, Calendar calendar) {
        try {
            if (calendar != null) {
                view.setText(DateFormat.getDate(calendar.getTimeInMillis(), DateFormat.DAY_FORMAT));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

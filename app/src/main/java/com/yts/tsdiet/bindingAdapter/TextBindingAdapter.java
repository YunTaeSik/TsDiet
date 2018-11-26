package com.yts.tsdiet.bindingAdapter;

import android.text.format.DateUtils;
import android.widget.TextView;

import com.yts.tsdiet.utils.DateFormat;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

import androidx.databinding.BindingAdapter;

public class TextBindingAdapter {
    @BindingAdapter({"setCalendarHeaderText"})
    public static void setCalendarHeaderText(TextView view, Long date) {
        try {
            if (date != null) {
                int month = DateFormat.getCalendar(date).get(Calendar.MONTH);
                String text = new DateFormatSymbols(Locale.ENGLISH).getMonths()[month].toUpperCase();
                view.setText(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

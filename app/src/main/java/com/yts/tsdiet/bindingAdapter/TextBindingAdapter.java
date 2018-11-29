package com.yts.tsdiet.bindingAdapter;

import android.content.Context;
import android.widget.TextView;

import com.yts.tsdiet.R;
import com.yts.tsdiet.data.model.Record;
import com.yts.tsdiet.data.model.RecordFood;
import com.yts.tsdiet.utils.DateFormat;
import com.yts.tsdiet.utils.Type;

import java.util.Calendar;

import androidx.databinding.BindingAdapter;

public class TextBindingAdapter {
    @BindingAdapter({"setText"})
    public static void setText(TextView view, double value) {
        try {
            view.setText(String.valueOf(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setKcalText"})
    public static void setKcalText(TextView view, double value) {
        try {
            view.setText("(" + String.valueOf(value) + "kcal)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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

    @BindingAdapter({"setNutrientPercentText", "setType"})
    public static void setNutrientPercentText(TextView view, Record record, String type) {
        Context context = view.getContext();

        String text = "";
        if (record != null) {
            double carbohydrate = record.getTotalCarbohydrate();
            double protein = record.getTotalProtein();
            double fat = record.getTotalFat();

            double total = carbohydrate + protein + fat == 0.0 ? 100.0 : carbohydrate + protein + fat;

            if (type.equals(Type.CARBOHYDRATE)) {
                text = context.getString(R.string.carbohydrate) + " (" + ((carbohydrate / total) * (100.0)) + "%)";
            } else if (type.equals(Type.PROTEIN)) {
                text = context.getString(R.string.protein) + " (" + ((protein / total) * (100.0)) + "%)";
            } else if (type.equals(Type.FAT)) {
                text = context.getString(R.string.fat) + " (" + ((fat / total) * (100.0)) + "%)";
            }
            view.setText(text);
        }
    }

    @BindingAdapter({"setNutrientPercentText", "setType"})
    public static void setNutrientPercentText(TextView view, RecordFood recordFood, String type) {
        Context context = view.getContext();

        String text = "";
        if (recordFood != null) {
            double carbohydrate = recordFood.getCarbohydrate();
            double protein = recordFood.getProtein();
            double fat = recordFood.getFat();

            double total = carbohydrate + protein + fat == 0.0 ? 100.0 : carbohydrate + protein + fat;

            if (type.equals(Type.CARBOHYDRATE)) {
                text = context.getString(R.string.carbohydrate) + " (" + ((carbohydrate / total) * (100.0)) + "%)";
            } else if (type.equals(Type.PROTEIN)) {
                text = context.getString(R.string.protein) + " (" + ((protein / total) * (100.0)) + "%)";
            } else if (type.equals(Type.FAT)) {
                text = context.getString(R.string.fat) + " (" + ((fat / total) * (100.0)) + "%)";
            }
            view.setText(text);
        }
    }
}

package com.yts.tsdiet.bindingAdapter;

import android.content.Context;
import android.widget.TextView;

import com.yts.tsdiet.R;
import com.yts.tsdiet.data.model.Record;
import com.yts.tsdiet.data.model.RecordFood;
import com.yts.tsdiet.utils.DateFormat;
import com.yts.tsdiet.utils.Type;
import com.yts.tsdiet.utils.ValueFormat;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import androidx.databinding.BindingAdapter;

public class TextBindingAdapter {
    @BindingAdapter({"setWeightText"})
    public static void setWeightText(TextView view, double value) {
        try {
            String text = String.valueOf(value);
            view.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setText"})
    public static void setText(TextView view, double value) {
        try {
            String text = ValueFormat.format(value);
            view.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setKcalText"})
    public static void setKcalText(TextView view, double value) {
        try {
            String text = "(" + ValueFormat.format(value) + "kcal)";
            view.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setGoalText"})
    public static void setGoalText(TextView view, float value) {
        try {
            String text = String.valueOf(value);
            view.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setDateRangeText"})
    public static void setDateRangeText(TextView view, Integer typeInteger) {
        Context context = view.getContext();
        int type = typeInteger != null ? typeInteger : 1;
        String text;
        switch (type) {
            case 0:
                text = context.getString(R.string.this_month);
                break;
            case 1:
                text = context.getString(R.string.this_year);
                break;
            case 2:
                text = context.getString(R.string.all);
                break;
            default:
                text = context.getString(R.string.this_year);
                break;
        }
        view.setText(text);
    }

    @BindingAdapter({"setKindText"})
    public static void setKindText(TextView view, Integer typeInteger) {
        Context context = view.getContext();
        int type = typeInteger != null ? typeInteger : 1;
        String text;
        switch (type) {
            case 0:
                text = context.getString(R.string.weight);
                break;
            case 1:
                text = context.getString(R.string.calorie);
                break;
            case 2:
                text = context.getString(R.string.all);
                break;
            default:
                text = context.getString(R.string.all);
                break;
        }
        view.setText(text);
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
                GregorianCalendar gregorianCalendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                view.setText(DateFormat.getDate(gregorianCalendar.getTimeInMillis(), DateFormat.DAY_FORMAT));
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
                text = context.getString(R.string.carbohydrate) + " (" + ValueFormat.format((carbohydrate / total) * (100.0)) + "%)";
            } else if (type.equals(Type.PROTEIN)) {
                text = context.getString(R.string.protein) + " (" + ValueFormat.format((protein / total) * (100.0)) + "%)";
            } else if (type.equals(Type.FAT)) {
                text = context.getString(R.string.fat) + " (" + ValueFormat.format((fat / total) * (100.0)) + "%)";
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
                text = context.getString(R.string.carbohydrate) + " (" + ValueFormat.format((carbohydrate / total) * (100.0)) + "%)";
            } else if (type.equals(Type.PROTEIN)) {
                text = context.getString(R.string.protein) + " (" + ValueFormat.format((protein / total) * (100.0)) + "%)";
            } else if (type.equals(Type.FAT)) {
                text = context.getString(R.string.fat) + " (" + ValueFormat.format((fat / total) * (100.0)) + "%)";
            }
            view.setText(text);
        }
    }

    @BindingAdapter({"setRecordFoodListTitleText"})
    public static void setRecordFoodListTitleText(TextView view, RecordFood recordFood) {
        try {
            String text = recordFood.getName() +
                    " " + ValueFormat.format(recordFood.getKcal()) + "kcal";
            view.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter({"setRecordFoodListSubTitleText"})
    public static void setRecordFoodListSubTitleText(TextView view, RecordFood recordFood) {
        try {
            Context context = view.getContext();
            //탄수화물 : 200g 단백질 : 200g 지방 : 200g\n수량 : 1 개 중량 : 200g
            String text = context.getString(R.string.carbohydrate) + " : " + ValueFormat.format(recordFood.getCarbohydrate()) + "g "
                    + context.getString(R.string.protein) + " : " + ValueFormat.format(recordFood.getProtein()) + "g "
                    + context.getString(R.string.fat) + " : " + ValueFormat.format(recordFood.getFat()) + "g" + "\n"
                    + context.getString(R.string.quantity) + " : " + recordFood.getQuantity() + context.getString(R.string.count) + " "
                    + context.getString(R.string.weight_two) + " : " + ValueFormat.format(recordFood.getSize()) + "g";
            view.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

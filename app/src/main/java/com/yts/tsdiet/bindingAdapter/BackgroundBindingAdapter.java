package com.yts.tsdiet.bindingAdapter;

import android.content.Context;

import com.yts.tsdiet.R;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

public class BackgroundBindingAdapter {
    @BindingAdapter({"setBackground", "setGoal"})
    public static void setBackground(AppCompatTextView view, Double value, Double goalValue) {
        Context context = view.getContext();
        double val = value != null ? value : 0.0;
        double goal = goalValue != null ? goalValue : 0.0;
        if (goal < val) {
            view.setBackground(ContextCompat.getDrawable(context, R.drawable.background_circle_red));
        } else {
            view.setBackground(ContextCompat.getDrawable(context, R.drawable.background_circle_blue));
        }
    }
}

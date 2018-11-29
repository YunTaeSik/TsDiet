package com.yts.tsdiet.bindingAdapter;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.BindingAdapter;

public class VisibleBindingAdapter {

    @BindingAdapter("setVisible")
    public static void setVisible(AppCompatImageView view, Boolean visibleBoolean) {
        boolean visible = visibleBoolean != null ? visibleBoolean : false;
        if (visible) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }
}

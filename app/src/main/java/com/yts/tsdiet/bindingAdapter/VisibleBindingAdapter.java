package com.yts.tsdiet.bindingAdapter;

import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yts.tsdiet.data.model.Record;

import java.util.List;

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

    @BindingAdapter("setVisible")
    public static void setVisible(FloatingActionButton view, Boolean visibleBoolean) {
        boolean visible = visibleBoolean != null ? visibleBoolean : false;
        if (visible) {
            view.show();
        } else {
            view.hide();
        }
    }

    @BindingAdapter("setVisible")
    public static void setVisible(MaterialButton view, Record record) {
        try {
            boolean visible = (record.getRecordFoodList() == null || record.getRecordFoodList().size() == 0);
            if (visible) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter("setVisible")
    public static void setVisible(FloatingActionButton view, List<Object> recordList) {
        try {
            if (recordList != null && recordList.size() > 0) {
                Record record = (Record) recordList.get(0);
                boolean visible = (record.getRecordFoodList() == null || record.getRecordFoodList().size() == 0);
                if (visible) {
                    view.show();
                } else {
                    view.hide();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

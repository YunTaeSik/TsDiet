package com.yts.tsdiet.bindingAdapter;

import com.yts.tsdiet.ui.adapter.DayAdapter;

import java.util.ArrayList;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdapaterBindingAdapter {
    @BindingAdapter("setDayListAdapter")
    public static void setDayListAdapter(RecyclerView view, ArrayList<Object> dayList) {
        DayAdapter adapter = (DayAdapter) view.getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
            //    adapter.setDayList(dayList);
        } else {
            GridLayoutManager manager = new GridLayoutManager(view.getContext(), 7);
            adapter = new DayAdapter(dayList);
            adapter.setHasStableIds(true);
            view.setLayoutManager(manager);
            view.setAdapter(adapter);
        }
    }
}

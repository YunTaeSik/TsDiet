package com.yts.tsdiet.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.yts.tsdiet.R;
import com.yts.tsdiet.databinding.DayItemBinding;
import com.yts.tsdiet.databinding.EmptyDayBinding;
import com.yts.tsdiet.viewmodel.CalendarViewModel;
import com.yts.tsdiet.viewmodel.EmptyViewModel;

import java.util.Calendar;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class DayAdapter extends RecyclerView.Adapter {
    private final int EMPTY_TYPE = 0;
    private final int DAY_TYPE = 1;

    private List<Object> mDayList;

    public DayAdapter(List<Object> dayList) {
        mDayList = dayList;
    }

    public void setDayList(List<Object> dayList) {
        mDayList = dayList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        Object item = mDayList.get(position);
        if (item instanceof String) {
            return EMPTY_TYPE;
        } else {
            return DAY_TYPE;
        }
    }

    @Override
    public long getItemId(int position) {
        Object item = mDayList.get(position);
        if (item instanceof String) {
            return (item).hashCode();
        } else if (item instanceof Calendar) {
            return ((Calendar) item).getTimeInMillis();
        } else {
            return position;
        }
    }

    @Override
    public int getItemCount() {
        if (mDayList != null) {
            return mDayList.size();
        }
        return 0;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == EMPTY_TYPE) {
            EmptyDayBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_day_empty, parent, false);
            return new EmptyViewHolder(binding);
        }
        DayItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_day, parent, false);
        return new DayViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == EMPTY_TYPE) {
            EmptyViewHolder holder = (EmptyViewHolder) viewHolder;
            EmptyViewModel model = new EmptyViewModel();
            holder.setViewModel(model);
        } else if (viewType == DAY_TYPE) {
            DayViewHolder holder = (DayViewHolder) viewHolder;
            Object item = mDayList.get(position);
            CalendarViewModel model = new CalendarViewModel();
            if (item instanceof Calendar) {
                model.setCalendar((Calendar) item);
                model.setRecord();
            }
            holder.setViewModel(model);
        }
    }

    private class EmptyViewHolder extends RecyclerView.ViewHolder {
        private EmptyDayBinding binding;

        public EmptyViewHolder(@NonNull EmptyDayBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(EmptyViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }

    }

    private class DayViewHolder extends RecyclerView.ViewHolder {
        private DayItemBinding binding;

        public DayViewHolder(@NonNull DayItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setViewModel(CalendarViewModel model) {
            binding.setModel(model);
            binding.executePendingBindings();
        }
    }
}
